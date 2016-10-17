package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.NightModeEvent;
import com.codeest.geeknews.model.bean.VersionBean;
import com.codeest.geeknews.model.http.MyHttpResponse;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.MainContract;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.RxUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by codeest on 16/8/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        registerEvent();
    }

    void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(NightModeEvent.class)
                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Func1<NightModeEvent, Boolean>() {
                    @Override
                    public Boolean call(NightModeEvent nightModeEvent) {
                        return nightModeEvent.getNightMode();
                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("切换模式失败ヽ(≧Д≦)ノ");
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void checkVersion(final String currentVersion) {
        Subscription rxSubscription = mRetrofitHelper.fetchVersionInfo()
                .compose(RxUtil.<MyHttpResponse<VersionBean>>rxSchedulerHelper())
                .compose(RxUtil.<VersionBean>handleMyResult())
                .subscribe(new Action1<VersionBean>() {
                    @Override
                    public void call(VersionBean versionBean) {
                        if (Integer.valueOf(currentVersion.replace(".", "")) < Integer.valueOf(versionBean.getCode().replace(".", ""))) {
                            mView.showUpdateDialog(versionBean);
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
}
