package com.github.yftx.AndroidHacks.sectionPatterns.mvp.model.impl;

import com.github.yftx.AndroidHacks.sectionPatterns.mvp.model.IOpenStatus;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class OpenStatus implements IOpenStatus{
    public Type type;

    @Override
    public Type getViewType() {
        return type;
    }

    @Override
    public void setViewType(Type type) {
        this.type = type;
    }

}
