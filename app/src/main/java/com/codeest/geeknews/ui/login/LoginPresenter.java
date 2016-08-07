package com.codeest.geeknews.ui.login;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.bean.UserCardInfo;
import com.codeest.geeknews.model.http.HttpResponse;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.RxUtil;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by codeest on 2016/8/2.
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public LoginPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getUserCardData(int uid,String token,int otherId) {
        mRetrofitHelper.fetchUserCardInfo(uid, token, otherId)
                .compose(RxUtil.<HttpResponse<UserCardInfo>>rxSchedulerHelper())
                .compose(RxUtil.<UserCardInfo>handleResult())
                .subscribe(new Subscriber<UserCardInfo>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.d("completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(e.toString());
                    }

                    @Override
                    public void onNext(UserCardInfo info) {
                        LogUtil.d(info.getNick());
                        mView.showResult(info);
                    }
                });
    }
}
