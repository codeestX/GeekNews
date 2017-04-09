package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.ThemeChildListBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.presenter.contract.ThemeChildContract;
import com.codeest.geeknews.util.RxUtil;
import com.codeest.geeknews.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by codeest on 16/8/24.
 */

public class ThemeChildPresenter extends RxPresenter<ThemeChildContract.View> implements ThemeChildContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    public ThemeChildPresenter(RetrofitHelper mRetrofitHelper, RealmHelper mRealmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void getThemeChildData(int id) {
        addSubscribe(mRetrofitHelper.fetchThemeChildListInfo(id)
                .compose(RxUtil.<ThemeChildListBean>rxSchedulerHelper())
                .map(new Function<ThemeChildListBean, ThemeChildListBean>() {
                    @Override
                    public ThemeChildListBean apply(ThemeChildListBean themeChildListBean) {
                        List<ThemeChildListBean.StoriesBean> list = themeChildListBean.getStories();
                        for(ThemeChildListBean.StoriesBean item : list) {
                            item.setReadState(mRealmHelper.queryNewsId(item.getId()));
                        }
                        return themeChildListBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<ThemeChildListBean>(mView) {
                    @Override
                    public void onNext(ThemeChildListBean themeChildListBean) {
                        mView.showContent(themeChildListBean);
                    }
                })
        );
    }

    @Override
    public void insertReadToDB(int id) {
        mRealmHelper.insertNewsId(id);
    }
}
