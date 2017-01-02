package com.codeest.geeknews.ui.vtex.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.TopicListBean;
import com.codeest.geeknews.presenter.VtexPresenter;
import com.codeest.geeknews.presenter.contract.VtexContract;
import com.codeest.geeknews.ui.vtex.adapter.TopicAdapter;
import com.codeest.geeknews.util.SnackbarUtil;
import com.codeest.geeknews.widget.ProgressImageView;
import com.codeest.geeknews.widget.CommonItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 6/12/19.
 */

public class VtexPagerFragment extends BaseFragment<VtexPresenter> implements VtexContract.View{

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.iv_progress)
    ProgressImageView ivProgress;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private TopicAdapter mAdapter;

    private String mType;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_common_list;
    }

    @Override
    protected void initEventAndData() {
        mType = getArguments().getString(Constants.IT_VTEX_TYPE);
        mAdapter = new TopicAdapter(mContext, new ArrayList<TopicListBean>());
        CommonItemDecoration mDecoration = new CommonItemDecoration(1, CommonItemDecoration.UNIT_DP);
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(mAdapter);
        rvContent.addItemDecoration(mDecoration);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getContent(mType);
            }
        });
        ivProgress.start();
        mPresenter.getContent(mType);
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvContent, msg);
    }

    @Override
    public void showContent(List<TopicListBean> mList) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        mAdapter.updateData(mList);
    }
}
