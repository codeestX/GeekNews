package com.codeest.geeknews.ui.gank.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codeest.geeknews.R;
import com.codeest.geeknews.app.App;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.base.SimpleActivity;
import com.codeest.geeknews.model.bean.RealmLikeBean;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.util.ShareUtil;
import com.codeest.geeknews.util.SystemUtil;
import com.codeest.geeknews.util.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by codeest on 16/8/20.
 */

public class GirlDetailActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_girl_detail)
    ImageView ivGirlDetail;

    private static int ACTION_SAVE = 0x00;
    private static int ACTION_SHARE = 0x01;

    Bitmap bitmap;
    RxPermissions rxPermissions;
    RealmHelper mRealmHelper;
    PhotoViewAttacher mAttacher;
    MenuItem menuItem;

    String url;
    String id;

    boolean isLiked;

    @Override
    protected int getLayout() {
        return R.layout.activity_girl_detail;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolBar,"");
        mRealmHelper = App.getAppComponent().realmHelper();
        Intent intent = getIntent();
        url = intent.getExtras().getString(Constants.IT_GANK_GRIL_URL);
        id = intent.getExtras().getString(Constants.IT_GANK_GRIL_ID);
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
        menuItem = menu.findItem(R.id.action_like);
        setLikeState(mRealmHelper.queryLikeId(id));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_like:
                if(isLiked) {
                    item.setIcon(R.mipmap.ic_toolbar_like_n);
                    mRealmHelper.deleteLikeBean(this.id);
                    isLiked = false;
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_like_p);
                    RealmLikeBean bean = new RealmLikeBean();
                    bean.setId(this.id);
                    bean.setImage(url);
                    bean.setType(Constants.TYPE_GIRL);
                    bean.setTime(System.currentTimeMillis());
                    mRealmHelper.insertLikeBean(bean);
                    isLiked = true;
                }
                break;
            case R.id.action_save:
                checkPermissionAndAction(ACTION_SAVE);
                break;
            case R.id.action_share:
                checkPermissionAndAction(ACTION_SHARE);
                break;
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

    private void checkPermissionAndAction(final int action) {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (granted) {
                            if (action == ACTION_SAVE) {
                                SystemUtil.saveBitmapToFile(mContext, url, bitmap, ivGirlDetail, false);
                            } else if (action == ACTION_SHARE) {
                                ShareUtil.shareImage(mContext, SystemUtil.saveBitmapToFile(mContext, url, bitmap, ivGirlDetail, true), "分享一只妹纸");
                            }
                        } else {
                            ToastUtil.shortShow("获取写入权限失败");
                        }
                    }
                });
    }
}
