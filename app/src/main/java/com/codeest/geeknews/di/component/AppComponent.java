package com.codeest.geeknews.di.component;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.di.module.AppModule;
import com.codeest.geeknews.di.module.HttpModule;
import com.codeest.geeknews.di.module.PageModule;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.ui.gank.fragment.GankMainFragment;
import com.codeest.geeknews.ui.gold.fragment.GoldMainFragment;
import com.codeest.geeknews.ui.main.fragment.AboutFragment;
import com.codeest.geeknews.ui.main.fragment.LikeFragment;
import com.codeest.geeknews.ui.main.fragment.SettingFragment;
import com.codeest.geeknews.ui.vtex.fragment.VtexMainFragment;
import com.codeest.geeknews.ui.wechat.fragment.WechatMainFragment;
import com.codeest.geeknews.ui.zhihu.fragment.ZhihuMainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, PageModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ZhihuMainFragment zhihuMainFragment();

    GankMainFragment gankMainFragment();

    WechatMainFragment wechatMainFragment();

    GoldMainFragment goldMainFragment();

    VtexMainFragment vtexMainFragment();

    LikeFragment likeFragment();

    SettingFragment settingFragment();

    AboutFragment aboutFragment();
}
