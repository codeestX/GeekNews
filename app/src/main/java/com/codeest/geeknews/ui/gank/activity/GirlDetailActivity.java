package com.codeest.geeknews.ui.gank.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codeest.geeknews.R;
import com.codeest.geeknews.base.SimpleActivity;
import com.codeest.geeknews.component.ImageLoader;
import com.codeest.geeknews.util.ImageUtil;
import com.codeest.geeknews.util.SystemUtil;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by codeest on 16/8/20.
 */

public class GirlDetailActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_girl_detail)
    ImageView ivGirlDetail;

    PhotoViewAttacher mAttacher;

    String url;
    Bitmap bitmap;

    @Override
    protected int getLayout() {
        return R.layout.activity_girl_detail;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolBar,"");
        Intent intent = getIntent();
        url = intent.getExtras().getString("url");
        if (url != null) {
            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    bitmap = resource;
                    ivGirlDetail.setImageBitmap(resource);
                    mAttacher = new PhotoViewAttacher(ivGirlDetail);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girl_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                SystemUtil.saveBitmapToFile(mContext,url,bitmap);
                break;
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
