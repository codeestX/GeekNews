package com.codeest.geeknews.base;

/**
 * Created by codeest on 2016/8/2.
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
