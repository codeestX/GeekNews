package com.codeest.geeknews.ui.gank.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.App;
import com.codeest.geeknews.base.SimpleActivity;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.util.ShareUtil;
import com.codeest.geeknews.util.SharedPreferenceUtil;
import com.codeest.geeknews.util.SystemUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/20.
 */

public class TechDetailActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.wv_tech_content)
    WebView wvTechContent;
    @BindView(R.id.view_loading)
    RotateLoading viewLoading;

    RealmHelper mRealmHelper;
    MenuItem menuItem;

    String title,url,id,tech;
    boolean isLiked;

    @Override
    protected int getLayout() {
        return R.layout.activity_tech_detail;
    }

    @Override
    protected void initEventAndData() {
        mRealmHelper = App.getAppComponent().realmHelper();
        Intent intent = getIntent();
        tech = intent.getExtras().getString("tech");
        title = intent.getExtras().getString("title");
        url = intent.getExtras().getString("url");
        id = intent.getExtras().getString("id");
        setToolBar(toolBar,title);
        WebSettings settings = wvTechContent.getSettings();
        if (SharedPreferenceUtil.getNoImageState()) {
            settings.setBlockNetworkImage(true);
        }
        if (SharedPreferenceUtil.getAutoCacheState()) {
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
                if (newProgress == 100) {
                    viewLoading.stop();
                } else {
                    if (!viewLoading.isStart()) {
                        viewLoading.start();
                    }
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
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_like_p);
                    RealmLikeBean bean = new RealmLikeBean();
                    bean.setId(this.id);
                    bean.setImage(url);
                    bean.setTitle(title);
                    bean.setType(TechPresenter.getTechType(tech));
                    bean.setTime(System.currentTimeMillis());
                    mRealmHelper.insertLikeBean(bean);
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
}
