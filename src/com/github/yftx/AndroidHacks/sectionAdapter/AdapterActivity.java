package com.github.yftx.AndroidHacks.sectionAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class AdapterActivity extends Activity {
    private ListView mListView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_activity);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setEmptyView(findViewById(R.id.empty));
    }

    public void onClickShowContent(View view) {
        mListView.setAdapter(new SampleAdapter(getLayoutInflater()));
    }
}