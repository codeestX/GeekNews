package com.codeest.geeknews.ui.main;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;
import com.codeest.geeknews.presenter.MainPresenter;
import com.codeest.geeknews.presenter.contract.MainContract;
import com.codeest.geeknews.util.LogUtil;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/9.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{

    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;

    ActionBarDrawerToggle mDrawerToggle;

    int currentNavigationId = 0;

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
        setToolBar(mToolbar,"首页");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                LogUtil.d(menuItem.getTitle().toString());
                menuItem.setChecked(true); // 改变item选中状态
                setTitle(menuItem.getTitle()); // 改变页面标题，标明导航状态
                currentNavigationId = menuItem.getItemId();
                mDrawerLayout.closeDrawers(); // 关闭导航菜单
                return true;
            }
        });
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
