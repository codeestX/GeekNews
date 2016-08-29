package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.WXItemBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.model.http.WXHttpResponse;
import com.codeest.geeknews.presenter.contract.WechatContract;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {

    public static final String TECH_WECHAT = "微信";
    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WechatPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getWechatData() {
        currentPage = 1;
        Subscription rxSubscription = mRetrofitHelper.fetchWechatListInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribe(new Action1<List<WXItemBean>>() {
                    @Override
                    public void call(List<WXItemBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.d(throwable.toString());
                        mView.showError("数据加载失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreWechatData() {
        Subscription rxSubscription = mRetrofitHelper.fetchWechatListInfo(NUM_OF_PAGE,++currentPage)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribe(new Action1<List<WXItemBean>>() {
                    @Override
                    public void call(List<WXItemBean> wxItemBeen) {
                        mView.showMoreContent(wxItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
