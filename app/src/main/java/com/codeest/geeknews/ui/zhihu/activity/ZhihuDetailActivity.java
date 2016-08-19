package com.codeest.geeknews.ui.zhihu.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.BaseActivity;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.model.bean.DetailExtraBean;
import com.codeest.geeknews.model.bean.ZhihuDetailBean;
import com.codeest.geeknews.presenter.ZhihuDetailPresenter;
import com.codeest.geeknews.presenter.contract.ZhihuDetailContract;
import com.codeest.geeknews.util.HtmlUtil;
import com.codeest.geeknews.util.ToastUtil;
import com.victor.loading.rotate.RotateLoading;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by codeest on 16/8/13.
 */

public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {

    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.view_loading)
    RotateLoading viewLoading;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;

    int id = 0;
    int allNum = 0;
    int shortNum = 0;
    int longNum = 0;
    boolean isBottomShow = true;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(viewToolbar,"");
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        mPresenter.getDetailData(id);
        mPresenter.getExtraData(id);
        viewLoading.start();
        wvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    llDetailBottom.animate().translationY(llDetailBottom.getHeight());
                } else if(scrollY - oldScrollY < 0 && !isBottomShow){    //上移出现
                    isBottomShow = true;
                    llDetailBottom.animate().translationY(0);
                }
            }
        });
    }

    @Override
    public void showContent(ZhihuDetailBean zhihuDetailBean) {
        viewLoading.stop();
        ImageLoader.load(mContext, zhihuDetailBean.getImage(), detailBarImage);
        clpToolbar.setTitle(zhihuDetailBean.getTitle());
        detailBarCopyright.setText(zhihuDetailBean.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(),zhihuDetailBean.getCss(),zhihuDetailBean.getJs());
        wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public void showExtraInfo(DetailExtraBean detailExtraBean) {
        viewLoading.stop();
        tvDetailBottomLike.setText(String.format("%d个赞",detailExtraBean.getPopularity()));
        tvDetailBottomComment.setText(String.format("%d条评论",detailExtraBean.getComments()));
        allNum = detailExtraBean.getComments();
        shortNum = detailExtraBean.getShort_comments();
        longNum = detailExtraBean.getLong_comments();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvDetailContent.canGoBack()) {
            wvDetailContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }

    @Override
    public void showError(String msg) {
        viewLoading.stop();
        ToastUtil.shortShow("获取信息失败");
    }

    @OnClick(R.id.tv_detail_bottom_comment)
    void gotoComment() {
        Intent intent = getIntent();
        intent.setClass(this,CommentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("allNum",allNum);
        intent.putExtra("shortNum",shortNum);
        intent.putExtra("longNum",longNum);
        startActivity(intent);
    }
}
