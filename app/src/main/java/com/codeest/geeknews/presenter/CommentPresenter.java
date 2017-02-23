package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.CommentBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.CommentContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by codeest on 16/8/18.
 */

public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter{

    public static final int SHORT_COMMENT = 0;

    public static final int LONG_COMMENT = 1;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CommentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getCommentData(int id, int commentKind) {
        if(commentKind == SHORT_COMMENT) {
            Subscription rxSubscription = mRetrofitHelper.fetchShortCommentInfo(id)
                    .compose(RxUtil.<CommentBean>rxSchedulerHelper())
                    .subscribe(new CommonSubscriber<CommentBean>(mView) {
                        @Override
                        public void onNext(CommentBean commentBean) {
                            mView.showContent(commentBean);
                        }
                    });
            addSubscrebe(rxSubscription);
        } else {
            Subscription rxSubscription = mRetrofitHelper.fetchLongCommentInfo(id)
                    .compose(RxUtil.<CommentBean>rxSchedulerHelper())
                    .subscribe(new CommonSubscriber<CommentBean>(mView) {
                        @Override
                        public void onNext(CommentBean commentBean) {
                            mView.showContent(commentBean);
                        }
                    });
            addSubscrebe(rxSubscription);
        }
    }
}
