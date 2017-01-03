package com.codeest.geeknews.ui.vtex.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.codeest.geeknews.ui.vtex.fragment.VtexMainFragment;
import com.codeest.geeknews.ui.vtex.fragment.VtexPagerFragment;

import java.util.List;

/**
 * Created by codeest on 16/12/22.
 */

public class VtexPagerAdapter extends FragmentPagerAdapter{

    private List<VtexPagerFragment> fragments;

    public VtexPagerAdapter(FragmentManager fm, List<VtexPagerFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return VtexMainFragment.typeStr[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
