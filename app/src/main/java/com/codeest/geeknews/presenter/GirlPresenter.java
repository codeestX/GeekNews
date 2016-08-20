package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.GirlContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/19.
 */

public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public GirlPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

}
