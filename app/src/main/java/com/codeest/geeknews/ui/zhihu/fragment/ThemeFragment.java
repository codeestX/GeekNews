package com.codeest.geeknews.ui.zhihu.fragment;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.presenter.ThemePresenter;
import com.codeest.geeknews.presenter.contract.ThemeContract;

/**
 * Created by codeest on 2016/8/11.
 */
public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View{

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    protected void initEventAndData() {

    }
}
