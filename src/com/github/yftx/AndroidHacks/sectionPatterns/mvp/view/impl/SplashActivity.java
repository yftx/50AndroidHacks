package com.github.yftx.AndroidHacks.sectionPatterns.mvp.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.yftx.AndroidHacks.R;
import com.github.yftx.AndroidHacks.sectionPatterns.mvp.model.IOpenStatus;
import com.github.yftx.AndroidHacks.sectionPatterns.mvp.presenter.SplashPresenter;
import com.github.yftx.AndroidHacks.sectionPatterns.mvp.view.ISplashView;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class SplashActivity extends Activity implements ISplashView {
    private TextView mTextView;
    private SplashPresenter mPresenter = new SplashPresenter();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_layout);
        mTextView = (TextView) findViewById(R.id.tv);
        mPresenter.setView(this);
    }

    public void onClickRefresh(View view) {
        switch (view.getId()) {
            case R.id.normal:
                mPresenter.refresh(IOpenStatus.Type.Normal);
                break;
            case R.id.small:
                mPresenter.refresh(IOpenStatus.Type.Small);
                break;
            case R.id.big:
                mPresenter.refresh(IOpenStatus.Type.Big);
                break;
        }
    }


    @Override
    public void showNormal() {
        mTextView.setText("Normal");
    }

    @Override
    public void showSmall() {
        mTextView.setText("Small");
    }

    @Override
    public void showBig() {
        mTextView.setText("Big");
    }
}