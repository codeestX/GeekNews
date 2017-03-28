package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.NodeListBean;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.model.bean.RepliesListBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.RepliesContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by codeest on 16/12/23.
 */

public class RepliesPresenter extends RxPresenter<RepliesContract.View> implements RepliesContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    public RepliesPresenter(RetrofitHelper mRetrofitHelper, RealmHelper mRealmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void getContent(String topic_id) {
        addSubscribe(mRetrofitHelper.fetchRepliesList(topic_id)
                .compose(RxUtil.<List<RepliesListBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<RepliesListBean>>(mView) {
                    @Override
                    public void onNext(List<RepliesListBean> repliesListBeen) {
                        mView.showContent(repliesListBeen);
                    }
                })
        );
    }

    @Override
    public void getTopInfo(String topic_id) {
        addSubscribe(mRetrofitHelper.fetchTopicInfo(topic_id)
                .compose(RxUtil.<List<NodeListBean>>rxSchedulerHelper())
                .filter(new Predicate<List<NodeListBean>>() {
                    @Override
                    public boolean test(@NonNull List<NodeListBean> nodeListBeen) throws Exception {
                        return nodeListBeen.size() > 0;
                    }
                })
                .map(new Function<List<NodeListBean>, NodeListBean>() {
                    @Override
                    public NodeListBean apply(List<NodeListBean> nodeListBeen) {
                        return nodeListBeen.get(0);
                    }
                })
                .subscribeWith(new CommonSubscriber<NodeListBean>(mView) {
                    @Override
                    public void onNext(NodeListBean nodeListBean) {
                        mView.showTopInfo(nodeListBean);
                    }
                })
        );
    }

    @Override
    public void insert(RealmLikeBean bean) {
        mRealmHelper.insertLikeBean(bean);
    }

    @Override
    public void delete(String id) {
        mRealmHelper.deleteLikeBean(id);
    }

    @Override
    public boolean query(String id) {
        return mRealmHelper.queryLikeId(id);
    }
}
