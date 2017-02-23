package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.ThemeListBean;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.ThemeContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/12.
 */

public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getThemeData() {
        mRetrofitHelper.fetchDailyThemeListInfo()
                .compose(RxUtil.<ThemeListBean>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<ThemeListBean>(mView) {
                    @Override
                    public void onNext(ThemeListBean themeListBean) {
                        mView.showContent(themeListBean);
                    }
                });
    }
}
