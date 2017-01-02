package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.NodeBean;
import com.codeest.geeknews.model.bean.NodeListBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.NodeContract;
import com.codeest.geeknews.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/12/23.
 */

public class NodePresenter extends RxPresenter<NodeContract.View> implements NodeContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public NodePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getContent(String node_name) {
        Subscription rxSubscription = mRetrofitHelper.fetchTopicList(node_name)
                .compose(RxUtil.<List<NodeListBean>>rxSchedulerHelper())
                .subscribe(new Action1<List<NodeListBean>>() {
                    @Override
                    public void call(List<NodeListBean> nodeListBeen) {
                        mView.showContent(nodeListBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getTopInfo(String node_name) {
        Subscription rxSubscription = mRetrofitHelper.fetchNodeInfo(node_name)
                .compose(RxUtil.<NodeBean>rxSchedulerHelper())
                .subscribe(new Action1<NodeBean>() {
                    @Override
                    public void call(NodeBean nodeBean) {
                        mView.showTopInfo(nodeBean);
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
