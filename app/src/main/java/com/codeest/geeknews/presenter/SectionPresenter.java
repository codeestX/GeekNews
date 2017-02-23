package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.SectionListBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.SectionContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by codeest on 16/8/12.
 */

public class SectionPresenter extends RxPresenter<SectionContract.View> implements SectionContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getSectionData() {
        Subscription rxSubscription = mRetrofitHelper.fetchSectionListInfo()
                .compose(RxUtil.<SectionListBean>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<SectionListBean>(mView) {
                    @Override
                    public void onNext(SectionListBean sectionListBean) {
                        mView.showContent(sectionListBean);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
