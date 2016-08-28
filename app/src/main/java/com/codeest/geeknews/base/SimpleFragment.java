package com.codeest.geeknews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.di.component.DaggerFragmentComponent;
import com.codeest.geeknews.di.component.FragmentComponent;
import com.codeest.geeknews.di.module.FragmentModule;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by codeest on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends SupportFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initEventAndData();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}
