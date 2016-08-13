package com.codeest.geeknews.ui.zhihu.activity;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;
import com.codeest.geeknews.presenter.ZhihuDetailPresenter;
import com.codeest.geeknews.presenter.contract.ZhihuDetailContract;

/**
 * Created by codeest on 16/8/13.
 */

public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View{

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initEventAndData() {

    }
}
