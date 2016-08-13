package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.DailyBeforeListBean;
import com.codeest.geeknews.model.bean.DailyListBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.DailyContract;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.RxUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by codeest on 16/8/11.
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public DailyPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObserverable(CalendarDay.class)
                .flatMap(new Func1<CalendarDay, Observable<DailyBeforeListBean>>() {
                    @Override
                    public Observable<DailyBeforeListBean> call(CalendarDay calendarDay) {
                        mView.showProgress();
                        StringBuilder date = new StringBuilder(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth());
                        String day = String.valueOf(calendarDay.getDay());
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        date.append(month).append(day);
                        return mRetrofitHelper.fetchDailyBeforeListInfo(date.toString());
                    }
                }).compose(RxUtil.<DailyBeforeListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyBeforeListBean>() {
                   @Override
                   public void call(DailyBeforeListBean dailyBeforeListBean) {
                       int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0,4));
                       int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4,6));
                       int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6,8));
                       mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyBeforeListBean);
                   }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }});
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getDailyData() {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyListBean>() {
                    @Override
                    public void call(DailyListBean dailyListBean) {
                        mView.showContent(dailyListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.d(throwable.toString());
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getBeforeData(String date) {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyBeforeListInfo(date)
                .compose(RxUtil.<DailyBeforeListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyBeforeListBean>() {
                    @Override
                    public void call(DailyBeforeListBean dailyBeforeListBean) {
                        int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0,4));
                        int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4,6));
                        int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6,8));
                        mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyBeforeListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
