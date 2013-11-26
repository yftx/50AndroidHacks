package com.github.yftx.AndroidHacks;

import android.R;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAnimation();
        setListAdapter(getSampleAdapter());
    }

    private void initAnimation() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(100);
        set.addAnimation(animation);
        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);
        //每个子view显示的间隔1.0表示子view按顺序依次显示，每次只显示一个动画，一个动画显示完再显示另外一个
        LayoutAnimationController controller = new LayoutAnimationController(set, 1.0f);
        getListView().setLayoutAnimation(controller);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ActivityInfo info = (ActivityInfo) l.getItemAtPosition(position);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, info.name));
        this.startActivity(intent);
    }

    public ListAdapter getSampleAdapter() {
        ArrayList<ActivityInfo> samples = new ArrayList<ActivityInfo>();
        String thisClazzName = getClass().getName();

        try {
            PackageInfo packageInfo = getPackageManager()
                    .getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activities = packageInfo.activities;
            for (ActivityInfo info : activities) {
                if (!thisClazzName.equals(info.name)) {
                    samples.add(info);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return new SampleAdapter(this, samples);
    }


    private static class SampleAdapter extends BaseAdapter {
        private final ArrayList<ActivityInfo> mItems;
        private final LayoutInflater mInflater;

        public SampleAdapter(Context context, ArrayList<ActivityInfo> activitys) {
            mItems = activitys;
            mInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public ActivityInfo getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = (TextView) convertView;
            if (tv == null) {
                tv = (TextView) mInflater.inflate(R.layout.simple_list_item_1, parent, false);
            }
            ActivityInfo info = getItem(position);
            if (!TextUtils.isEmpty(info.nonLocalizedLabel)) {
                tv.setText(info.nonLocalizedLabel);
            } else {
                tv.setText(info.labelRes);
            }
            return tv;
        }
    }

}
