package com.codeest.geeknews.ui.zhihu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codeest.geeknews.ui.zhihu.fragment.CommentFragment;

/**
 * Created by codeest on 16/8/19.
 */

public class CommentMainAdapter extends FragmentPagerAdapter{

    private int id;

    public CommentMainAdapter(FragmentManager fm,int id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CommentFragment shortCommentFragment = new CommentFragment();
                Bundle shortBundle = new Bundle();
                shortBundle.putInt("id", id);
                shortBundle.putInt("kind", 0);
                shortCommentFragment.setArguments(shortBundle);
                break;
            case 1:
                CommentFragment longCommentFragment = new CommentFragment();
                Bundle longBundle = new Bundle();
                longBundle.putInt("id", id);
                longBundle.putInt("kind", 1);
                longCommentFragment.setArguments(longBundle);
                break;
        }
        return new CommentFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
