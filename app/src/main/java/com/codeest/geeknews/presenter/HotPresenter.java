package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.HotContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/12.
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HotPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

}
