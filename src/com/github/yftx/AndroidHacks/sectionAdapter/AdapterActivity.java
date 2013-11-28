package com.github.yftx.AndroidHacks.sectionAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-28
 *
 * pinned section listview 的实现同样可以参考
 * https://github.com/beworker/pinned-section-listview.git
 *
 */
public class AdapterActivity extends Activity {
    private ListView mListView;
    private TextView header;
    private int topVisiblePosition = -1;
    private SampleAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_activity);
        header = (TextView) findViewById(R.id.header);
        header.setVisibility(View.GONE);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setEmptyView(findViewById(R.id.empty));
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem != topVisiblePosition) {
                    topVisiblePosition = firstVisibleItem;
                    refreshToppHeader(topVisiblePosition);
                }
            }

            private void refreshToppHeader(int topVisiblePosition) {
                if (mAdapter == null) return;
                final String text = mAdapter.getItem(topVisiblePosition);
                header.setText(text.substring(0,1));
            }
        });
    }

    public void onClickShowContent(View view) {
        mAdapter = new SampleAdapter(getLayoutInflater());
        mListView.setAdapter(mAdapter);
        header.setVisibility(View.VISIBLE);
    }
}