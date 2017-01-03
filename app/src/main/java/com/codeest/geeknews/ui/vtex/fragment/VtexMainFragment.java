package com.codeest.geeknews.ui.vtex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.SimpleFragment;
import com.codeest.geeknews.ui.vtex.activity.NodeActivity;
import com.codeest.geeknews.ui.vtex.adapter.VtexPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by codeest on 16/12/19.
 */

public class VtexMainFragment extends SimpleFragment {

    @BindView(R.id.tab_vtex_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_vtex_main)
    ViewPager mViewPager;

    public static String[] typeStr = {"技术", "创意", "好玩", "Apple", "酷工作", "交易", "城市", "问与答", "最热", "全部", "R2"};
    public static String[] type = {"tech", "creative", "play", "apple", "jobs", "deals", "city", "qna", "hot", "all", "r2"};

    List<VtexPagerFragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vtex_main;
    }

    @Override
    protected void initEventAndData() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < type.length; i++) {
            VtexPagerFragment fragment = new VtexPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.IT_VTEX_TYPE, type[i]);
            fragment.setArguments(bundle);
            mTabLayout.addTab(mTabLayout.newTab());
            fragments.add(fragment);
        }
        VtexPagerAdapter mAdapter = new VtexPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.iv_vtex_menu)
    void gotoNode() {
        Intent intent = new Intent(getActivity(), NodeActivity.class);
        mContext.startActivity(intent);
    }
}
