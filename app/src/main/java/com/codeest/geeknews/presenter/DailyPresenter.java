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

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by codeest on 16/8/11.
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    private Subscription intervalSubscription;

    private static final int INTERVAL_INSTANCE = 6;

    private int topCount = 0;
    private int currentTopCount = 0;

    @Inject
    public DailyPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(CalendarDay.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())  //为了显示progress切到主线程
                .map(new Func1<CalendarDay, String>() {
                    @Override
                    public String call(CalendarDay calendarDay) {
                        mView.showProgress();
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth() + 1);
                        String day = String.valueOf(calendarDay.getDay());
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                }).observeOn(Schedulers.io())   //为了网络请求切到io线程
                .flatMap(new Func1<String, Observable<DailyBeforeListBean>>() {
                    @Override
                    public Observable<DailyBeforeListBean> call(String date) {
                        return mRetrofitHelper.fetchDailyBeforeListInfo(date);
                    }
                }).observeOn(AndroidSchedulers.mainThread())    //为了显示结果切到主线程
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
                        registerEvent();
                        LogUtil.d(throwable.toString());
                        mView.showError("数据加载失败");
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
                        topCount = dailyListBean.getTop_stories().size();
                        mView.showContent(dailyListBean);
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
                        mView.showError("数据加载失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void startInterval() {
        intervalSubscription = Observable.interval(INTERVAL_INSTANCE, TimeUnit.SECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(currentTopCount == topCount)
                            currentTopCount = 0;
                        mView.doInterval(currentTopCount++);
                    }
                });
        addSubscrebe(intervalSubscription);
    }

    @Override
    public void stopInterval() {
        intervalSubscription.unsubscribe();
    }
}
