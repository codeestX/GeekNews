package com.codeest.geeknews.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.codeest.geeknews.ui.zhihu.fragment.DailyFragment;
import com.codeest.geeknews.ui.zhihu.fragment.ThemeFragment;

import java.util.List;

/**
 * Created by codeest on 16/8/11.
 */

public class ZhihuMainAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;

    public ZhihuMainAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
