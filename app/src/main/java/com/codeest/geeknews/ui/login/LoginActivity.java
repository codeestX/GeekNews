package com.codeest.geeknews.ui.login;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;
import com.codeest.geeknews.bean.UserCardInfo;
import com.codeest.geeknews.util.LogUtil;

import butterknife.BindView;

/**
 * Created by codeest on 2016/8/2.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.test)
    TextView mTest;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

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
        setToolBar(mToolBar, "StarShow");
        mPresenter.getUserCardData(21001035, "3036318864", 21001043);
    }

    @Override
    public void showResult(UserCardInfo info) {
        mTest.setText(info.getNick());
    }
}