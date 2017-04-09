package com.codeest.geeknews.presenter;

import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.WXItemBean;
import com.codeest.geeknews.model.event.SearchEvent;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.model.http.response.WXHttpResponse;
import com.codeest.geeknews.presenter.contract.WechatContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {

    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WechatPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        registerEvent();
    }

    @Override
    public void getWechatData() {
        queryStr = null;
        currentPage = 1;
        addSubscribe(mRetrofitHelper.fetchWechatListInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView) {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                })
        );
    }

    @Override
    public void getMoreWechatData() {
        Flowable<WXHttpResponse<List<WXItemBean>>> observable;
        if (queryStr != null) {
            observable = mRetrofitHelper.fetchWechatSearchListInfo(NUM_OF_PAGE,++currentPage,queryStr);
        } else {
            observable = mRetrofitHelper.fetchWechatListInfo(NUM_OF_PAGE,++currentPage);
        }
        addSubscribe(observable
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView, "没有更多了ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showMoreContent(wxItemBeen);
                    }
                })
        );
    }

    private void getSearchWechatData(String query) {
        currentPage = 1;
        addSubscribe(mRetrofitHelper.fetchWechatSearchListInfo(NUM_OF_PAGE,currentPage,query)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView) {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                })
        );
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(SearchEvent.class)
                .compose(RxUtil.<SearchEvent>rxSchedulerHelper())
                .filter(new Predicate<SearchEvent>() {
                    @Override
                    public boolean test(@NonNull SearchEvent searchEvent) throws Exception {
                        return searchEvent.getType() == Constants.TYPE_WECHAT;
                    }
                })
                .map(new Function<SearchEvent, String>() {
                    @Override
                    public String apply(SearchEvent searchEvent) {
                        return searchEvent.getQuery();
                    }
                })
                .subscribeWith(new CommonSubscriber<String>(mView, "搜索失败ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(String s) {
                        queryStr = s;
                        getSearchWechatData(s);
                    }
                })
        );
    }
}
