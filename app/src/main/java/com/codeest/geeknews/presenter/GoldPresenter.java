package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.GoldListBean;
import com.codeest.geeknews.model.http.GoldHttpResponse;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.GoldContract;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.RxUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldPresenter extends RxPresenter<GoldContract.View> implements GoldContract.Presenter{

    private static final int NUM_EACH_PAGE = 20;
    private static final int NUM_HOT_LIMIT = 3;

    private RetrofitHelper mRetrofitHelper;
    private List<GoldListBean> totalList = new ArrayList<>();

    private boolean isHotList = true;
    private int currentPage = 0;
    private String mType;

    @Inject
    public GoldPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getGoldData(String type) {
        mType = type;
        currentPage = 0;
        totalList.clear();
        Observable<List<GoldListBean>> list = mRetrofitHelper.fetchGoldList(type, NUM_EACH_PAGE, currentPage++)
                .compose(RxUtil.<GoldHttpResponse<List<GoldListBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GoldListBean>>handleGoldResult());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);

        Observable<List<GoldListBean>> hotList = mRetrofitHelper.fetchGoldHotList(type,
                new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()), NUM_HOT_LIMIT)
                .compose(RxUtil.<GoldHttpResponse<List<GoldListBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GoldListBean>>handleGoldResult());

        Subscription rxSubscription = Observable.concat(hotList, list).subscribe(new Action1<List<GoldListBean>>() {
            @Override
            public void call(List<GoldListBean> goldListBean) {
                if (isHotList) {
                    isHotList = false;
                    totalList.addAll(goldListBean);
                } else {
                    isHotList = true;
                    totalList.addAll(goldListBean);
                    mView.showContent(totalList);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtil.d(throwable.toString());
            }
        });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreGoldData() {
        Subscription rxSubscription = mRetrofitHelper.fetchGoldList(mType, NUM_EACH_PAGE, currentPage++)
                .compose(RxUtil.<GoldHttpResponse<List<GoldListBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GoldListBean>>handleGoldResult())
                .subscribe(new Action1<List<GoldListBean>>() {
                    @Override
                    public void call(List<GoldListBean> goldListBeen) {
                        totalList.addAll(goldListBeen);
                        mView.showMoreContent(totalList, totalList.size(), totalList.size() + NUM_EACH_PAGE);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
