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

    }
}
