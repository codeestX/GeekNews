package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.VersionBean;
import com.codeest.geeknews.model.http.MyHttpResponse;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.SettingContract;
import com.codeest.geeknews.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/10/17.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SettingPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
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
                        } else {
                            mView.showError("已经是最新版本~");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("获取版本信息失败 T T");
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
