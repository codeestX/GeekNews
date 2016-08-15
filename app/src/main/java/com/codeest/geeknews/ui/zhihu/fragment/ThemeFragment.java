package com.codeest.geeknews.ui.zhihu.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.ThemeListBean;
import com.codeest.geeknews.presenter.ThemePresenter;
import com.codeest.geeknews.presenter.contract.ThemeContract;
import com.codeest.geeknews.ui.zhihu.adapter.ThemeAdapter;
import com.codeest.geeknews.util.ToastUtil;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 2016/8/11.
 */
public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View {

    @BindView(R.id.rv_theme_list)
    RecyclerView rvThemeList;
    @BindView(R.id.view_loading)
    RotateLoading viewLoading;

    ThemeAdapter mAdapter;
    List<ThemeListBean.OthersBean> mList = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    protected void initEventAndData() {
        mAdapter = new ThemeAdapter(mContext, mList);
        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {

            }
        });
        rvThemeList.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvThemeList.setAdapter(mAdapter);
        mPresenter.getThemeData();
        rvThemeList.setVisibility(View.INVISIBLE);
        viewLoading.start();
    }

    @Override
    public void showContent(ThemeListBean themeListBean) {
        viewLoading.stop();
        rvThemeList.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(themeListBean.getOthers());
    }

    @Override
    public void showError(String msg) {
        viewLoading.stop();
        rvThemeList.setVisibility(View.VISIBLE);
        ToastUtil.shortShow(msg);
    }
}
