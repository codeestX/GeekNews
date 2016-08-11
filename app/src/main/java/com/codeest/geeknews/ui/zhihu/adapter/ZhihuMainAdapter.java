package com.codeest.geeknews.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by codeest on 16/8/11.
 */

public class ZhihuMainAdapter extends FragmentPagerAdapter{
    public ZhihuMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
