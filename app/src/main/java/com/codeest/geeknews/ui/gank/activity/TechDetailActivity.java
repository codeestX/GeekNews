package com.codeest.geeknews.ui.gank.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.codeest.geeknews.R;
import com.codeest.geeknews.app.App;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.SimpleActivity;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.util.ShareUtil;
import com.codeest.geeknews.util.SystemUtil;
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

    String title;
    String url;
    String id;
    String tech;
    RealmHelper mRealmHelper;
    MenuItem menuItem;

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
        mRealmHelper.queryLikeId(id);
        setToolBar(toolBar,title);
        WebSettings settings = wvTechContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
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
        return true;
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_like:
                if(item.isChecked()) {
                    item.setChecked(false);
                    mRealmHelper.deleteLikeBean(this.id);
                } else {
                    item.setChecked(true);
                    RealmLikeBean bean = new RealmLikeBean();
                    bean.setId(this.id);
                    bean.setImage(url);
                    bean.setType(getTechType(tech));
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

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }

    private int getTechType(String tech) {
        switch (tech) {
            case TechPresenter.TECH_ANDROID:
                return Constants.TYPE_ANDROID;
            case TechPresenter.TECH_IOS:
                return Constants.TYPE_IOS;
            case TechPresenter.TECH_WEB:
                return Constants.TYPE_WEB;
        }
        return Constants.TYPE_ANDROID;
    }
}
