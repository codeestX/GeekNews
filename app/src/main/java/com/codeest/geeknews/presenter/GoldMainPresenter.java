package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.bean.GoldManagerBean;
import com.codeest.geeknews.model.bean.GoldManagerItemBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.presenter.contract.GoldMainContract;
import com.codeest.geeknews.util.SharedPreferenceUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.realm.RealmList;

/**
 * Created by codeest on 16/11/28.
 */

public class GoldMainPresenter extends RxPresenter<GoldMainContract.View> implements GoldMainContract.Presenter{

    private RealmHelper mRealmHelper;
    private RealmList<GoldManagerItemBean> mList;

    @Inject
    public GoldMainPresenter(RealmHelper mRealHelper) {
        this.mRealmHelper = mRealHelper;
        registerEvent();
    }

    private void registerEvent() {
        addRxBusSubscribe(GoldManagerBean.class, new Consumer<GoldManagerBean>() {
            @Override
            public void accept(GoldManagerBean goldManagerBean) {
                mRealmHelper.updateGoldManagerList(goldManagerBean);
                mView.updateTab(goldManagerBean.getManagerList());
            }
        });
    }

    @Override
    public void initManagerList() {
        if (SharedPreferenceUtil.getManagerPoint()) {
            //第一次使用，生成默认ManagerList
            initList();
            mRealmHelper.updateGoldManagerList(new GoldManagerBean(mList));
            mView.updateTab(mList);
        } else {
            if (mRealmHelper.getGoldManagerList() == null) {
                initList();
                mRealmHelper.updateGoldManagerList(new GoldManagerBean(mList));
            } else {
                mList = mRealmHelper.getGoldManagerList().getManagerList();
            }
            mView.updateTab(mList);
        }
    }

    @Override
    public void setManagerList() {
        mView.jumpToManager(mRealmHelper.getGoldManagerList());
    }

    private void initList() {
        mList = new RealmList<>();
        mList.add(new GoldManagerItemBean(0, true));
        mList.add(new GoldManagerItemBean(1, true));
        mList.add(new GoldManagerItemBean(2, true));
        mList.add(new GoldManagerItemBean(3, true));
        mList.add(new GoldManagerItemBean(4, false));
        mList.add(new GoldManagerItemBean(5, false));
        mList.add(new GoldManagerItemBean(6, false));
        mList.add(new GoldManagerItemBean(7, false));
    }
}
