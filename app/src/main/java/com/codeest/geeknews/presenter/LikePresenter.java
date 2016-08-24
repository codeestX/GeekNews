package com.codeest.geeknews.presenter;

import com.codeest.geeknews.base.RxPresenter;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.presenter.contract.LikeContract;

import javax.inject.Inject;

/**
 * Created by codeest on 2016/8/23.
 */
public class LikePresenter extends RxPresenter<LikeContract.View> implements LikeContract.Presenter{

    private RealmHelper mRealmHelper;

    @Inject
    public LikePresenter(RealmHelper mRealmHelper) {
        this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void getLikeData() {
        if (mRealmHelper.getLikeList().size() == 0) {
//            mView.showError("还没有收藏哦~");
        } else {
            mView.showContent(mRealmHelper.getLikeList());
        }
    }

    @Override
    public void deleteLikeData(String id) {
        mRealmHelper.deleteLikeBean(id);
    }
}
