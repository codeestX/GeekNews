package com.codeest.geeknews.ui.main;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;

/**
 * Created by codeest on 16/8/9.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
