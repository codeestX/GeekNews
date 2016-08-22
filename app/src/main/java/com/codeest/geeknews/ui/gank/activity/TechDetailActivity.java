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
import com.codeest.geeknews.base.SimpleActivity;
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

    @Override
    protected int getLayout() {
        return R.layout.activity_tech_detail;
    }

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        url = intent.getExtras().getString("url");
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
        return true;
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
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
}
