package com.github.yftx.AndroidHacks.sectionTipsAndTricks;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-27
 */
public class ScreenOrientation extends FragmentActivity {
    private TextView mTitle;
    private ViewPager mViewPager;
    private View mMockView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_orientation);
        mTitle = (TextView) findViewById(R.id.title);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mMockView = findViewById(R.id.port_view_pos);
        initDate();
        refreshView();
    }

    private void initDate() {
        mViewPager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        refreshView();
        super.onConfigurationChanged(newConfig);
    }

    private void refreshView() {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                showLandPattern();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
            case Configuration.ORIENTATION_SQUARE:
            case Configuration.ORIENTATION_UNDEFINED:
            default:
                showPortPattern();
                break;
        }

    }

    private void showPortPattern() {
        mTitle.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mMockView.getLayoutParams();
        mViewPager.setLayoutParams(params);
    }

    private void showLandPattern() {
        mTitle.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mViewPager.setLayoutParams(params);
    }




}