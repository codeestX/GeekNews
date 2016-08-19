package com.codeest.geeknews.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codeest.geeknews.ui.zhihu.fragment.CommentFragment;

import java.util.List;

/**
 * Created by codeest on 16/8/19.
 */

public class CommentMainAdapter extends FragmentPagerAdapter{

    List<CommentFragment> fragments;

    public CommentMainAdapter(FragmentManager fm,List<CommentFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragments.get(0);
            case 1:
                return fragments.get(1);
        }
        return fragments.get(0);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
