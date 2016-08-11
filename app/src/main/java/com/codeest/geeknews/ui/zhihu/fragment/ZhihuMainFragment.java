package com.codeest.geeknews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.SimpleFragment;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/11.
 */

public class ZhihuMainFragment extends SimpleFragment{

    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;

    String[] tabTitle = new String[]{"日报","主题","专栏","热门"};

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
