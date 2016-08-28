package com.codeest.geeknews.ui.zhihu.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.SectionListBean;
import com.codeest.geeknews.presenter.SectionPresenter;
import com.codeest.geeknews.presenter.contract.SectionContract;
import com.codeest.geeknews.ui.zhihu.adapter.SectionAdapter;
import com.codeest.geeknews.util.ToastUtil;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 2016/8/11.
 */
public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionContract.View {

    @BindView(R.id.rv_section_list)
    RecyclerView rvSectionList;
    @BindView(R.id.view_loading)
    RotateLoading viewLoading;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<SectionListBean.DataBean> mList;
    SectionAdapter mAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new SectionAdapter(mContext,mList);
        rvSectionList.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvSectionList.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewLoading.start();
                mPresenter.getSectionData();
            }
        });
        mPresenter.getSectionData();
        viewLoading.start();
    }

    @Override
    public void showError(String msg) {
        swipeRefresh.setRefreshing(false);
        viewLoading.stop();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(SectionListBean sectionListBean) {
        swipeRefresh.setRefreshing(false);
        viewLoading.stop();
        mList.clear();
        mList.addAll(sectionListBean.getData());
        mAdapter.notifyDataSetChanged();
    }
}
