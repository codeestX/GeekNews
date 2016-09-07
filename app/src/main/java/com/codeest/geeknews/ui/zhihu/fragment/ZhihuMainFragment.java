package com.codeest.geeknews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.SimpleFragment;
import com.codeest.geeknews.ui.zhihu.adapter.ZhihuMainAdapter;

import java.util.ArrayList;
import java.util.List;

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
    List<Fragment> fragments = new ArrayList<Fragment>();

    ZhihuMainAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new DailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(mAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);
    }
}
