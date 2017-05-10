package com.codeest.geeknews.ui.gank.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.App;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.SimpleActivity;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.prefs.ImplPreferencesHelper;
import com.codeest.geeknews.util.ShareUtil;
import com.codeest.geeknews.util.SystemUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/20.
 */

public class TechDetailActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.wv_tech_content)
    WebView wvTechContent;
    @BindView(R.id.tv_progress)
    TextView tvProgress;

    RealmHelper mRealmHelper;
    ImplPreferencesHelper mImplPreferencesHelper;
    MenuItem menuItem;

    String title,url,imgUrl,id;
    int type;
    boolean isLiked;

    @Override
    protected int getLayout() {
        return R.layout.activity_tech_detail;
    }

    @Override
    protected void initEventAndData() {
        mRealmHelper = App.getAppComponent().realmHelper();
        mImplPreferencesHelper = App.getAppComponent().preferencesHelper();
        Intent intent = getIntent();
        type = intent.getExtras().getInt(Constants.IT_GANK_DETAIL_TYPE);
        title = intent.getExtras().getString(Constants.IT_GANK_DETAIL_TITLE);
        url = intent.getExtras().getString(Constants.IT_GANK_DETAIL_URL);
        imgUrl = intent.getExtras().getString(Constants.IT_GANK_DETAIL_IMG_URL);
        id = intent.getExtras().getString(Constants.IT_GANK_DETAIL_ID);
        setToolBar(toolBar,title);
        WebSettings settings = wvTechContent.getSettings();
        if (mImplPreferencesHelper.getNoImageState()) {
            settings.setBlockNetworkImage(true);
        }
        if (mImplPreferencesHelper.getAutoCacheState()) {
            settings.setAppCacheEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            if (SystemUtil.isNetworkConnected()) {
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }
        }
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvTechContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvTechContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (tvProgress == null)
                    return;
                if (newProgress == 100) {
                    tvProgress.setVisibility(View.GONE);
                } else {
                    tvProgress.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams lp = tvProgress.getLayoutParams();
                    lp.width = App.SCREEN_WIDTH * newProgress / 100;
                }
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvTechContent.loadUrl(url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvTechContent.canGoBack()) {
            wvTechContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun, menu);
        menuItem = menu.findItem(R.id.action_like);
        setLikeState(mRealmHelper.queryLikeId(id));
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_like:
                if(isLiked) {
                    item.setIcon(R.mipmap.ic_toolbar_like_n);
                    mRealmHelper.deleteLikeBean(this.id);
                    isLiked = false;
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_like_p);
                    RealmLikeBean bean = new RealmLikeBean();
                    bean.setId(this.id);
                    bean.setImage(imgUrl);
                    bean.setUrl(url);
                    bean.setTitle(title);
                    bean.setType(type);
                    bean.setTime(System.currentTimeMillis());
                    mRealmHelper.insertLikeBean(bean);
                    isLiked = true;
                }
                break;
            case R.id.action_copy:
                SystemUtil.copyToClipBoard(mContext,url);
                return true;
            case R.id.action_share:
                ShareUtil.shareText(mContext,url,"分享一篇文章");
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

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }

    public static class Builder {

        private String title;
        private String url;
        private String imgUrl;
        private String id;
        private int type;
        private View shareView;
        private Context mContext;
        private Activity mActivity;

        public Builder() {

        }

        public Builder setContext(Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setAnimConfig(Activity mActivity, View shareView) {
            this.mActivity = mActivity;
            this.shareView = shareView;
            return this;
        }
    }

    public static void launch(Builder builder) {
        if (builder.shareView != null) {
            Intent intent = new Intent();
            intent.setClass(builder.mContext, TechDetailActivity.class);
            intent.putExtra(Constants.IT_GANK_DETAIL_URL, builder.url);
            intent.putExtra(Constants.IT_GANK_DETAIL_IMG_URL, builder.imgUrl);
            intent.putExtra(Constants.IT_GANK_DETAIL_TITLE, builder.title);
            intent.putExtra(Constants.IT_GANK_DETAIL_ID, builder.id);
            intent.putExtra(Constants.IT_GANK_DETAIL_TYPE, builder.type);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(builder.mActivity, builder.shareView, "shareView");
            builder.mContext.startActivity(intent,options.toBundle());
        } else {
            Intent intent = new Intent();
            intent.setClass(builder.mContext, TechDetailActivity.class);
            intent.putExtra(Constants.IT_GANK_DETAIL_URL, builder.url);
            intent.putExtra(Constants.IT_GANK_DETAIL_IMG_URL, builder.imgUrl);
            intent.putExtra(Constants.IT_GANK_DETAIL_TITLE, builder.title);
            intent.putExtra(Constants.IT_GANK_DETAIL_ID, builder.id);
            intent.putExtra(Constants.IT_GANK_DETAIL_TYPE, builder.type);
            builder.mContext.startActivity(intent);
        }
    }
}
