package com.codeest.geeknews.ui.vtex.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.RootActivity;
import com.codeest.geeknews.model.bean.NodeListBean;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.model.bean.RepliesListBean;
import com.codeest.geeknews.model.http.api.VtexApis;
import com.codeest.geeknews.presenter.vtex.RepliesPresenter;
import com.codeest.geeknews.base.contract.vtex.RepliesContract;
import com.codeest.geeknews.ui.vtex.adapter.RepliesAdapter;
import com.codeest.geeknews.util.ShareUtil;
import com.codeest.geeknews.util.SystemUtil;
import com.codeest.geeknews.widget.CommonItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 16/12/19.
 */

public class RepliesActivity extends RootActivity<RepliesPresenter> implements RepliesContract.View {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_main)
    RecyclerView rvContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private RepliesAdapter mAdapter;
    private NodeListBean mTopBean;
    private MenuItem menuItem;
    private String topicId;
    private boolean isLiked;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_replies;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(toolBar, "帖子详情");
        topicId = getIntent().getExtras().getString(Constants.IT_VTEX_TOPIC_ID);
        mTopBean = getIntent().getParcelableExtra(Constants.IT_VTEX_REPLIES_TOP);
        mAdapter = new RepliesAdapter(mContext, new ArrayList<RepliesListBean>(), mTopBean);
        CommonItemDecoration mDecoration = new CommonItemDecoration(2, CommonItemDecoration.UNIT_PX);
        rvContent.addItemDecoration(mDecoration);
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getContent(topicId);
            }
        });
        stateLoading();
        mPresenter.getContent(topicId);
        if (mTopBean == null) {
            mPresenter.getTopInfo(topicId);
        }
    }


    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(List<RepliesListBean> mList) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mAdapter.setContentData(mList);
    }

    @Override
    public void showTopInfo(NodeListBean mTopInfo) {
        mTopBean = mTopInfo;
        mAdapter.setTopData(mTopInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun, menu);
        menuItem = menu.findItem(R.id.action_like);
        setLikeState(mPresenter.query(topicId));
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_like:
                if(isLiked) {
                    item.setIcon(R.mipmap.ic_toolbar_like_n);
                    mPresenter.delete(topicId);
                    isLiked = false;
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_like_p);
                    RealmLikeBean bean = new RealmLikeBean();
                    bean.setId(topicId);
                    bean.setImage(mTopBean.getMember().getavatar_normal());
//                    bean.setUrl(url);
                    bean.setTitle(mTopBean.getTitle());
                    bean.setType(Constants.TYPE_VTEX);
                    bean.setTime(System.currentTimeMillis());
                    mPresenter.insert(bean);
                    isLiked = true;
                }
                break;
            case R.id.action_copy:
                SystemUtil.copyToClipBoard(mContext, VtexApis.REPLIES_URL + topicId);
                return true;
            case R.id.action_share:
                ShareUtil.shareText(mContext, VtexApis.REPLIES_URL + topicId, "分享一篇文章");
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLikeState(boolean state) {
        if(state) {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            isLiked = true;
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            isLiked = false;
        }
    }
}
