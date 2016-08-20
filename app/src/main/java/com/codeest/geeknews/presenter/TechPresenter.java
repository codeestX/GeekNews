package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.model.http.HttpResponse;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.TechContract;
import com.codeest.geeknews.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/8/20.
 */

public class TechPresenter extends RxPresenter<TechContract.View> implements TechContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    public static final String TECH_ANDROID = "Android";
    public static final String TECH_IOS = "iOS";
    public static final String TECH_WEB = "前端";
    private static final int NUM_OF_PAGE = 10;

    private int currentPage = 1;

    @Inject
    public TechPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getGankData(String tech) {
        currentPage = 1;
        Subscription rxSubscription = mRetrofitHelper.fetchTechList(tech,NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        mView.showContent(gankItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载数据失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreGankData(String tech) {
        Subscription rxSubscription = mRetrofitHelper.fetchTechList(tech,NUM_OF_PAGE,currentPage++)
                .compose(RxUtil.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        mView.showMoreContent(gankItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载更多数据失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getGirlImage() {
        Subscription rxSubscription = mRetrofitHelper.fetchRandomGirl(1)
                .compose(RxUtil.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBean) {
                        mView.showGirlImage(gankItemBean.get(0).getUrl());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载封面失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
