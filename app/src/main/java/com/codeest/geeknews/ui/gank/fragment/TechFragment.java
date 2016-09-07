package com.codeest.geeknews.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.presenter.contract.TechContract;
import com.codeest.geeknews.ui.gank.activity.TechDetailActivity;
import com.codeest.geeknews.ui.gank.adapter.TechAdapter;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.SnackbarUtil;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/19.
 */

public class TechFragment extends BaseFragment<TechPresenter> implements TechContract.View {

    @BindView(R.id.rv_tech_content)
    RecyclerView rvTechContent;
    @BindView(R.id.view_loading)
    RotateLoading viewLoading;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

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
                intent.putExtra("id",mList.get(position).get_id());
                intent.putExtra("tech",tech);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        rvTechContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                View firstVisibleItem = recyclerView.getChildAt(0);
                int firstItemPosition = ((LinearLayoutManager) rvTechContent.getLayoutManager()).findFirstVisibleItemPosition();
                int itemHeight = firstVisibleItem.getHeight();
                int firstItemBottom = rvTechContent.getLayoutManager().getDecoratedBottom(firstVisibleItem);
                mAdapter.setTopAlpha(((firstItemPosition + 1) * itemHeight - firstItemBottom) * 2.0 / recyclerView.getChildAt(0).getHeight());
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGankData(tech);
            }
        });
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.stop();
        }
        SnackbarUtil.showShort(rvTechContent,msg);
    }

    @Override
    public void showContent(List<GankItemBean> list) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.stop();
        }
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
        mAdapter.setTopInfo(url,DateUtil.getCurrentDateString());
        mAdapter.notifyItemChanged(0);
    }
}
