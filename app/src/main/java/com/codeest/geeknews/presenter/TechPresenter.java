package com.codeest.geeknews.presenter;

import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.model.bean.GankSearchItemBean;
import com.codeest.geeknews.model.event.SearchEvent;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.model.http.response.GankHttpResponse;
import com.codeest.geeknews.presenter.contract.TechContract;
import com.codeest.geeknews.ui.gank.fragment.GankMainFragment;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by codeest on 16/8/20.
 */

public class TechPresenter extends RxPresenter<TechContract.View> implements TechContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;
    private String currentTech = GankMainFragment.tabTitle[0];
    private int currentType = Constants.TYPE_ANDROID;

    @Inject
    public TechPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(SearchEvent.class)
                .compose(RxUtil.<SearchEvent>rxSchedulerHelper())
                .filter(new Func1<SearchEvent, Boolean>() {
                    @Override
                    public Boolean call(SearchEvent searchEvent) {
                        return searchEvent.getType() == currentType;
                    }
                })
                .map(new Func1<SearchEvent, String>() {
                    @Override
                    public String call(SearchEvent searchEvent) {
                        return searchEvent.getQuery();
                    }
                })
                .subscribe(new CommonSubscriber<String>(mView, "搜索失败") {
                    @Override
                    public void onNext(String s) {
                        queryStr = s;
                        getSearchTechData();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    private void getSearchTechData() {
        currentPage = 1;
        Subscription rxSubscription = mRetrofitHelper.fetchGankSearchList(queryStr, currentTech, NUM_OF_PAGE, currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankSearchItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankSearchItemBean>>handleResult())
                .map(new Func1<List<GankSearchItemBean>, List<GankItemBean>>() {
                    @Override
                    public List<GankItemBean> call(List<GankSearchItemBean> gankSearchItemBeen) {
                        List<GankItemBean> newList = new ArrayList<>();
                        for (GankSearchItemBean item : gankSearchItemBeen) {
                            GankItemBean bean = new GankItemBean();
                            bean.set_id(item.getGanhuo_id());
                            bean.setDesc(item.getDesc());
                            bean.setPublishedAt(item.getPublishedAt());
                            bean.setWho(item.getWho());
                            bean.setUrl(item.getUrl());
                            newList.add(bean);
                        }
                        return newList;
                    }
                })
                .subscribe(new CommonSubscriber<List<GankItemBean>>(mView) {
                    @Override
                    public void onNext(List<GankItemBean> gankItemBeen) {
                        mView.showContent(gankItemBeen);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getGankData(String tech, int type) {
        queryStr = null;
        currentPage = 1;
        currentTech = tech;
        currentType = type;
        Subscription rxSubscription = mRetrofitHelper.fetchTechList(tech,NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankItemBean>>(mView) {
                    @Override
                    public void onNext(List<GankItemBean> gankItemBeen) {
                        mView.showContent(gankItemBeen);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreGankData(String tech) {
        if(queryStr != null) {
            getMoreSearchGankData();
            return;
        }
        Subscription rxSubscription = mRetrofitHelper.fetchTechList(tech,NUM_OF_PAGE,++currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankItemBean>>(mView, "加载更多数据失败ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(List<GankItemBean> gankItemBeen) {
                        mView.showMoreContent(gankItemBeen);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    private void getMoreSearchGankData() {
        Subscription rxSubscription = mRetrofitHelper.fetchGankSearchList(queryStr, currentTech, NUM_OF_PAGE, ++currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankSearchItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankSearchItemBean>>handleResult())
                .map(new Func1<List<GankSearchItemBean>, List<GankItemBean>>() {
                    @Override
                    public List<GankItemBean> call(List<GankSearchItemBean> gankSearchItemBeen) {
                        List<GankItemBean> newList = new ArrayList<>();
                        for (GankSearchItemBean item : gankSearchItemBeen) {
                            GankItemBean bean = new GankItemBean();
                            bean.set_id(item.getGanhuo_id());
                            bean.setDesc(item.getDesc());
                            bean.setPublishedAt(item.getPublishedAt());
                            bean.setWho(item.getWho());
                            bean.setUrl(item.getUrl());
                            newList.add(bean);
                        }
                        return newList;
                    }
                })
                .subscribe(new CommonSubscriber<List<GankItemBean>>(mView) {
                    @Override
                    public void onNext(List<GankItemBean> gankItemBeen) {
                        mView.showMoreContent(gankItemBeen);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getGirlImage() {
        Subscription rxSubscription = mRetrofitHelper.fetchRandomGirl(1)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankItemBean>>(mView, "加载封面失败") {
                    @Override
                    public void onNext(List<GankItemBean> gankItemBean) {
                        mView.showGirlImage(gankItemBean.get(0).getUrl(), gankItemBean.get(0).getWho());
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
