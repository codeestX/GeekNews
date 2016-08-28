package com.codeest.geeknews.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.Window;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;
import com.codeest.geeknews.presenter.MainPresenter;
import com.codeest.geeknews.presenter.contract.MainContract;
import com.codeest.geeknews.ui.gank.fragment.GankMainFragment;
import com.codeest.geeknews.ui.main.fragment.AboutFragment;
import com.codeest.geeknews.ui.main.fragment.LikeFragment;
import com.codeest.geeknews.ui.main.fragment.SettingFragment;
import com.codeest.geeknews.ui.wechat.fragment.WechatMainFragment;
import com.codeest.geeknews.ui.zhihu.fragment.ZhihuMainFragment;
import com.codeest.geeknews.util.LogUtil;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

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

    private static final String ITEM_ZHIHU = "知乎日报";
    private static final String ITEM_WECHAT = "微信精选";
    private static final String ITEM_GANK = "干货集中营";
    private static final String ITEM_LIKE = "收藏";
    private static final String ITEM_SETTING = "设置";
    private static final String ITEM_ABOUT = "关于";

    ActionBarDrawerToggle mDrawerToggle;
    ZhihuMainFragment mZhihuFragment;
    GankMainFragment mGankFragment;
    WechatMainFragment mWechatFragment;
    LikeFragment mLikeFragment;
    SettingFragment mSettingFragment;
    AboutFragment mAboutFragment;
    MenuItem mLastMenuItem;

    private String hideFragment = ITEM_ZHIHU;
    private String showFragment = ITEM_ZHIHU;

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
        setToolBar(mToolbar,ITEM_ZHIHU);
        mZhihuFragment = new ZhihuMainFragment();
        mGankFragment = new GankMainFragment();
        mWechatFragment = new WechatMainFragment();
        mLikeFragment = new LikeFragment();
        mSettingFragment = new SettingFragment();
        mAboutFragment = new AboutFragment();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_zhihu);
        loadMultipleRootFragment(R.id.fl_main_content,0,mZhihuFragment,mGankFragment,mWechatFragment,mLikeFragment,mSettingFragment,mAboutFragment);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_zhihu:
                        showFragment = ITEM_ZHIHU;
                        break;
                    case R.id.drawer_gank:
                        showFragment = ITEM_GANK;
                        break;
                    case R.id.drawer_wechat:
                        showFragment = ITEM_WECHAT;
                        break;
                    case R.id.drawer_setting:
                        showFragment = ITEM_SETTING;
                        break;
                    case R.id.drawer_like:
                        showFragment = ITEM_LIKE;
                        break;
                    case R.id.drawer_about:
                        showFragment = ITEM_ABOUT;
                        break;
                }
                if(mLastMenuItem != null) {
                    mLastMenuItem.setChecked(false);
                }
                mLastMenuItem = menuItem;
                menuItem.setChecked(true);
                mToolbar.setTitle(showFragment);
                mDrawerLayout.closeDrawers();
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                hideFragment = menuItem.getTitle().toString();
                return true;
            }
        });
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

    @Override
    public void showError(String msg) {

    }

    private SupportFragment getTargetFragment(String item) {
        switch (item) {
            case ITEM_ZHIHU:
                return mZhihuFragment;
            case ITEM_GANK:
                return mGankFragment;
            case ITEM_WECHAT:
                return mWechatFragment;
            case ITEM_LIKE:
                return mLikeFragment;
            case ITEM_SETTING:
                return mSettingFragment;
            case ITEM_ABOUT:
                return mAboutFragment;
        }
        return mZhihuFragment;
    }
}
