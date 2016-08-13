package com.codeest.geeknews.ui.zhihu.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.DailyBeforeListBean;
import com.codeest.geeknews.model.bean.DailyListBean;
import com.codeest.geeknews.presenter.DailyPresenter;
import com.codeest.geeknews.presenter.contract.DailyContract;
import com.codeest.geeknews.ui.zhihu.activity.CalendarActivity;
import com.codeest.geeknews.ui.zhihu.adapter.DailyAdapter;
import com.codeest.geeknews.util.CircularAnimUtil;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.util.ToastUtil;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by codeest on 2016/8/11.
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.view_loading)
    RotateLoading viewLoading;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rv_daily_list)
    RecyclerView rvDailyList;

    String currentDate;

    DailyAdapter mAdapter;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventAndData() {
        currentDate = DateUtil.getCurrentDate();
        mAdapter = new DailyAdapter(mContext,mList);
        mAdapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBeforeData(currentDate);
            }
        });
        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDailyList.setAdapter(mAdapter);
        rvDailyList.setVisibility(View.INVISIBLE);
        viewLoading.start();
        mPresenter.getDailyData();
    }

    @Override
    public void showContent(DailyListBean info) {
        viewLoading.stop();
        rvDailyList.setVisibility(View.VISIBLE);
        mAdapter.addDailyDate(info);
    }

    @Override
    public void showMoreContent(String date,DailyBeforeListBean info) {
        swipeRefresh.setRefreshing(false);
        currentDate = String.valueOf(Integer.valueOf(info.getDate() + 1));
        viewLoading.stop();
        rvDailyList.setVisibility(View.VISIBLE);
        mAdapter.addDailyBeforeDate(info);
    }

    @Override
    public void showError() {
        viewLoading.stop();
        ToastUtil.shortShow("数据加载失败");
    }

    @Override
    public void showProgress() {
        viewLoading.start();
        rvDailyList.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.fab_calender)
    void startCalender() {
        Intent it = new Intent();
        it.setClass(mContext,CalendarActivity.class);
        CircularAnimUtil.startActivity(mActivity,it,fabCalender,R.color.colorPrimary);
    }
}
