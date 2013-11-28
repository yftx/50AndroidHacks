package com.github.yftx.AndroidHacks.sectionPatterns.mvp.model;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public interface IOpenStatus {
    enum Type {
        Normal, Small, Big
    }

    Type getViewType();
    void setViewType(Type type);

}
