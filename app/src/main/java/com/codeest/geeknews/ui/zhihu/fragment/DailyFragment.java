package com.codeest.geeknews.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.App;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.component.RxBus;
import com.codeest.geeknews.model.bean.DailyBeforeListBean;
import com.codeest.geeknews.model.bean.DailyListBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.presenter.DailyPresenter;
import com.codeest.geeknews.presenter.contract.DailyContract;
import com.codeest.geeknews.ui.zhihu.activity.CalendarActivity;
import com.codeest.geeknews.ui.zhihu.activity.ZhihuDetailActivity;
import com.codeest.geeknews.ui.zhihu.adapter.DailyAdapter;
import com.codeest.geeknews.util.CircularAnimUtil;
import com.codeest.geeknews.util.DateUtil;
import com.codeest.geeknews.util.LogUtil;
import com.codeest.geeknews.util.ToastUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
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
    RealmHelper mRealHealper;
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
        mRealHealper = App.getAppComponent().realmHelper();
        currentDate = DateUtil.getCurrentDate();
        mAdapter = new DailyAdapter(mContext,mList);
        mAdapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,View shareView) {
                mRealHealper.insertNewsId(mList.get(position).getId());
                mAdapter.setReadState(position,true);
                if(mAdapter.getIsBefore()) {
                    mAdapter.notifyItemChanged(position + 1);
                } else {
                    mAdapter.notifyItemChanged(position + 2);
                }
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                LogUtil.d(shareView.getTransitionName());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rvDailyList.setVisibility(View.INVISIBLE);
                viewLoading.start();
                if(currentDate.equals("今日热闻")) {
                    mPresenter.getDailyData();
                } else {
                    int year = Integer.valueOf(currentDate.substring(0,4));
                    int month = Integer.valueOf(currentDate.substring(4,6));
                    int day = Integer.valueOf(currentDate.substring(6,8));
                    CalendarDay date = CalendarDay.from(year, month, day);
                    RxBus.getDefault().post(date);
//                    mPresenter.getBeforeData(currentDate);
                }
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
        mList = info.getStories();
        mAdapter.addDailyDate(info);
        mPresenter.startInterval();
    }

    @Override
    public void showMoreContent(String date,DailyBeforeListBean info) {
        mPresenter.stopInterval();
        swipeRefresh.setRefreshing(false);
        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()) + 1);
        viewLoading.stop();
        rvDailyList.setVisibility(View.VISIBLE);
        mAdapter.addDailyBeforeDate(info);
    }

    @Override
    public void showProgress() {
        viewLoading.start();
        rvDailyList.setVisibility(View.INVISIBLE);
    }

    @Override
    public void doInterval(int currentCount) {
        mAdapter.changeTopPager(currentCount);
    }

    @OnClick(R.id.fab_calender)
    void startCalender() {
        Intent it = new Intent();
        it.setClass(mContext,CalendarActivity.class);
        CircularAnimUtil.startActivity(mActivity,it,fabCalender,R.color.colorAccent);
    }

    @Override
    public void showError(String msg) {
        viewLoading.stop();
        ToastUtil.shortShow(msg);
    }
}
