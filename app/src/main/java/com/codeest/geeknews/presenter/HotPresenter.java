package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.HotListBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.HotContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by codeest on 16/8/12.
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    public HotPresenter(RetrofitHelper mRetrofitHelper,RealmHelper mRealHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mRealmHelper = mRealHelper;
    }

    @Override
    public void getHotData() {
        Subscription rxSubscription = mRetrofitHelper.fetchHotListInfo()
                .compose(RxUtil.<HotListBean>rxSchedulerHelper())
                .map(new Func1<HotListBean, HotListBean>() {
                    @Override
                    public HotListBean call(HotListBean hotListBean) {
                        List<HotListBean.RecentBean> list = hotListBean.getRecent();
                        for(HotListBean.RecentBean item : list) {
                            item.setReadState(mRealmHelper.queryNewsId(item.getNews_id()));
                        }
                        return hotListBean;
                    }
                })
                .subscribe(new CommonSubscriber<HotListBean>(mView) {
                    @Override
                    public void onNext(HotListBean hotListBean) {
                        mView.showContent(hotListBean);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void insertReadToDB(int id) {
        mRealmHelper.insertNewsId(id);
    }
}
