package com.github.yftx.AndroidHacks.sectionPatterns.mvp.presenter;

import com.github.yftx.AndroidHacks.sectionPatterns.mvp.model.IOpenStatus;
import com.github.yftx.AndroidHacks.sectionPatterns.mvp.model.impl.OpenStatus;
import com.github.yftx.AndroidHacks.sectionPatterns.mvp.view.ISplashView;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class SplashPresenter {
    private IOpenStatus mOpenStatus;
    private ISplashView mView;

    public SplashPresenter() {
        this(new OpenStatus());
    }

    public SplashPresenter(IOpenStatus openStatus) {
        mOpenStatus = openStatus;
    }

    public void setView(ISplashView view) {
        this.mView = view;
    }

    protected ISplashView getView() {
        return mView;
    }

    public void refresh(IOpenStatus.Type type){
        mOpenStatus.setViewType(type);
        handleRefresh();
    }

    public void handleRefresh() {
        switch (mOpenStatus.getViewType()){
            case Normal:
                mView.showNormal();
                break;
            case Small:
                mView.showSmall();
                break;
            case Big:
                mView.showBig();
                break;
        }
    }
}
