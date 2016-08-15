package com.codeest.geeknews.ui.zhihu.fragment;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.presenter.HotPresenter;
import com.codeest.geeknews.presenter.contract.HotContract;

/**
 * Created by codeest on 2016/8/11.
 */
public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View{

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
