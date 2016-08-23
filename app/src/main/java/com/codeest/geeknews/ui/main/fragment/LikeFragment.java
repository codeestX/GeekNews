package com.codeest.geeknews.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseFragment;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.presenter.LikePresenter;
import com.codeest.geeknews.presenter.contract.LikeContract;
import com.codeest.geeknews.ui.main.adapter.LikeAdapter;
import com.codeest.geeknews.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/23.
 */

public class LikeFragment extends BaseFragment<LikePresenter> implements LikeContract.View {

    @BindView(R.id.rv_like_list)
    RecyclerView rvLikeList;

    LikeAdapter mAdapter;
    List<RealmLikeBean> mList;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new LikeAdapter(mContext, mList);
        rvLikeList.setLayoutManager(new LinearLayoutManager(mContext));
        rvLikeList.setAdapter(mAdapter);
        mPresenter.getLikeData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getLikeData();
    }

    @Override
    public void showError(String msg) {
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(List<RealmLikeBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}
