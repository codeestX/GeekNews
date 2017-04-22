package com.codeest.geeknews.ui.gold.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.GoldManagerBean;
import com.codeest.geeknews.model.bean.GoldManagerItemBean;
import com.codeest.geeknews.presenter.gold.GoldMainPresenter;
import com.codeest.geeknews.base.contract.gold.GoldMainContract;
import com.codeest.geeknews.ui.gold.activity.GoldManagerActivity;
import com.codeest.geeknews.ui.gold.adapter.GoldPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldMainFragment extends BaseFragment<GoldMainPresenter> implements GoldMainContract.View {

    @BindView(R.id.tab_gold_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_gold_main)
    ViewPager mViewPager;

    public static String[] typeStr = {"Android", "iOS", "前端", "后端", "设计", "产品", "阅读", "工具资源"};
    public static String[] type = {"android", "ios", "frontend", "backend", "design", "product", "article", "freebie"};

    List<GoldPagerFragment> fragments = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_main;
    }

    @Override
    protected void initEventAndData() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.initManagerList();
    }

    @Override
    public void updateTab(List<GoldManagerItemBean> mList) {
        fragments.clear();
        mTabLayout.removeAllTabs();
        for (GoldManagerItemBean item : mList) {
            if (item.getIsSelect()) {
                GoldPagerFragment fragment = new GoldPagerFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.IT_GOLD_TYPE, type[item.getIndex()]);
                bundle.putString(Constants.IT_GOLD_TYPE_STR, typeStr[item.getIndex()]);
                mTabLayout.addTab(mTabLayout.newTab().setText(typeStr[item.getIndex()]));
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
        }
        GoldPagerAdapter mAdapter = new GoldPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        for (GoldManagerItemBean item : mList) {
            if (item.getIsSelect()) {
                mTabLayout.getTabAt(currentIndex++).setText(typeStr[item.getIndex()]);
            }
        }
        currentIndex = 0;
    }

    @Override
    public void jumpToManager(GoldManagerBean mBean) {
        Intent intent = new Intent(getActivity(), GoldManagerActivity.class);
        intent.putExtra(Constants.IT_GOLD_MANAGER, mBean);
        mContext.startActivity(intent);
    }

    @OnClick(R.id.iv_gold_menu)
    public void onClick(View view) {
        mPresenter.setManagerList();
    }
}
