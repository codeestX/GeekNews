package com.codeest.geeknews.ui.gank.fragment;

import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.presenter.GirlPresenter;
import com.codeest.geeknews.presenter.contract.GirlContract;

/**
 * Created by codeest on 16/8/19.
 */

public class GirlFragment extends BaseFragment<GirlPresenter> implements GirlContract.View{

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
