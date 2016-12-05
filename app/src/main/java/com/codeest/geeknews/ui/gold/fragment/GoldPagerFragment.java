package com.codeest.geeknews.ui.gold.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.GoldListBean;
import com.codeest.geeknews.presenter.GoldPresenter;
import com.codeest.geeknews.presenter.contract.GoldContract;
import com.codeest.geeknews.ui.gold.adapter.GoldListAdapter;
import com.codeest.geeknews.util.SnackbarUtil;
import com.codeest.geeknews.widget.GoldItemDecoration;
import com.codeest.geeknews.widget.ProgressImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldPagerFragment extends BaseFragment<GoldPresenter> implements GoldContract.View {

    @BindView(R.id.rv_gold_list)
    RecyclerView rvGoldList;
    @BindView(R.id.iv_progress)
    ProgressImageView ivProgress;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private GoldListAdapter mAdapter;
    private GoldItemDecoration mDecoration;

    private boolean isLoadingMore = false;
    private String mType;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initEventAndData() {
        mType = getArguments().getString(Constants.IT_GOLD_TYPE);
        mDecoration = new GoldItemDecoration();
        mAdapter = new GoldListAdapter(mContext, new ArrayList<GoldListBean>(), getArguments().getString(Constants.IT_GOLD_TYPE_STR));
        rvGoldList.setLayoutManager(new LinearLayoutManager(mContext));
        rvGoldList.setAdapter(mAdapter);
        rvGoldList.addItemDecoration(mDecoration);
        rvGoldList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) rvGoldList.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvGoldList.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreGoldData();
                    }
                }
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mAdapter.getHotFlag()) {
                    rvGoldList.addItemDecoration(mDecoration);
                }
                mAdapter.setHotFlag(true);
                mPresenter.getGoldData(mType);
            }
        });
        mAdapter.setOnHotCloseListener(new GoldListAdapter.OnHotCloseListener() {
            @Override
            public void onClose() {
                rvGoldList.removeItemDecoration(mDecoration);
            }
        });
        ivProgress.start();
        mPresenter.getGoldData(mType);
    }

    @Override
    public void showContent(List<GoldListBean> goldListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        mAdapter.updateData(goldListBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<GoldListBean> goldMoreListBean, int start, int end) {
        mAdapter.updateData(goldMoreListBean);
        mAdapter.notifyItemRangeInserted(start, end);
        isLoadingMore = false;
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvGoldList, msg);
    }
}
