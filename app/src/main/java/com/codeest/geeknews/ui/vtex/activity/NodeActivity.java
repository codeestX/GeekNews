package com.codeest.geeknews.ui.vtex.activity;

import android.content.res.XmlResourceParser;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.SimpleActivity;
import com.codeest.geeknews.ui.vtex.adapter.NodeAdapter;
import com.codeest.geeknews.util.XmlUtil;

import butterknife.BindView;

/**
 * Created by codeest on 16/12/19.
 */

public class NodeActivity extends SimpleActivity {

    NodeAdapter mAdapter;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_node_title)
    TextView tvNodeTitle;

    private LinearLayoutManager mManager;
    private ArrayMap<String, ArrayMap<String,String>> map;
    private int mTitleHeight;
    private int mCurrentPosition;

    @Override
    protected int getLayout() {
        return R.layout.activity_node;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolBar, "节点导航");
        XmlResourceParser xmlParser = this.getResources().getXml(R.xml.nodes);
        try {
            map = XmlUtil.parseNodes(xmlParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter = new NodeAdapter(mContext , map);
        mManager = new LinearLayoutManager(mContext);
        rvContent.setLayoutManager(mManager);
        rvContent.setAdapter(mAdapter);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mTitleHeight = tvNodeTitle.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = mManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mTitleHeight) {
                        tvNodeTitle.setY(-(mTitleHeight - view.getTop()));
                    } else {
                        tvNodeTitle.setY(0);
                    }
                }
                if (mCurrentPosition != mManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = mManager.findFirstVisibleItemPosition();
                    tvNodeTitle.setY(0);
                    if (map != null) {
                        tvNodeTitle.setText(map.keyAt(mCurrentPosition));
                    }
                }
            }
        });
        tvNodeTitle.setText(map.keyAt(0));
    }
}
