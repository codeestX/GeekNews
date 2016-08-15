package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.DetailExtraBean;
import com.codeest.geeknews.model.bean.ZhihuDetailBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.ZhihuDetailContract;
import com.codeest.geeknews.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/8/13.
 */

public class ZhihuDetailPresenter extends RxPresenter<ZhihuDetailContract.View> implements ZhihuDetailContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ZhihuDetailPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getDetailData(int id) {
        Subscription rxSubscription =  mRetrofitHelper.fetchDetailInfo(id)
                .compose(RxUtil.<ZhihuDetailBean>rxSchedulerHelper())
                .subscribe(new Action1<ZhihuDetailBean>() {
                    @Override
                    public void call(ZhihuDetailBean zhihuDetailBean) {
                        mView.showContent(zhihuDetailBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getExtraData(int id) {
        Subscription rxSubscription =  mRetrofitHelper.fetchDetailExtraInfo(id)
                .compose(RxUtil.<DetailExtraBean>rxSchedulerHelper())
                .subscribe(new Action1<DetailExtraBean>() {
                    @Override
                    public void call(DetailExtraBean detailExtraBean) {
                        mView.showExtraInfo(detailExtraBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("");
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
