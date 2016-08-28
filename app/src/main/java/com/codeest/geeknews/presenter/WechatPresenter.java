package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.WechatContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WechatPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getWechatData() {

    }

    @Override
    public void getMoreWechatData() {

    }
}
