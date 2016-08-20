package com.codeest.geeknews.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.presenter.contract.TechContract;
import com.codeest.geeknews.ui.gank.activity.TechDetailActivity;
import com.codeest.geeknews.ui.gank.adapter.TechAdapter;
import com.codeest.geeknews.ui.zhihu.activity.ZhihuDetailActivity;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.ToastUtil;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/19.
 */

public class TechFragment extends BaseFragment<TechPresenter> implements TechContract.View {

    @BindView(R.id.iv_tech_bar_image)
    ImageView ivTechBarImage;
    @BindView(R.id.tv_tech_bar_time)
    TextView tvTechBarTime;
    @BindView(R.id.rv_tech_content)
    RecyclerView rvTechContent;
    @BindView(R.id.view_loading)
    RotateLoading viewLoading;

    List<GankItemBean> mList;
    TechAdapter mAdapter;

    boolean isLoadingMore = false;
    String tech;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tech;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getGirlImage();
        tvTechBarTime.setText(DateUtil.getCurrentDateString());
        mList = new ArrayList<>();
        tech = getArguments().getString("tech");
        mAdapter = new TechAdapter(mContext,mList,tech);
        rvTechContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvTechContent.setAdapter(mAdapter);
        viewLoading.start();
        mPresenter.getGankData(tech);
        mAdapter.setOnItemClickListener(new TechAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareView) {
                Intent intent = new Intent();
                intent.setClass(mContext, TechDetailActivity.class);
                intent.putExtra("url",mList.get(position).getUrl());
                intent.putExtra("title",mList.get(position).getDesc());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        rvTechContent.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) rvTechContent.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvTechContent.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreGankData(tech);
                    }
                }
            }
        });
    }

    @Override
    public void showError(String msg) {
        viewLoading.stop();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(List<GankItemBean> list) {
        viewLoading.stop();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<GankItemBean> list) {
        viewLoading.stop();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        isLoadingMore = false;
    }

    @Override
    public void showGirlImage(String url) {
        ImageLoader.load(mContext, url, ivTechBarImage);
    }
}
