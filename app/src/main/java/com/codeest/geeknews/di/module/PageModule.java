package com.codeest.geeknews.di.module;

import com.codeest.geeknews.ui.gank.fragment.GankMainFragment;
import com.codeest.geeknews.ui.gold.fragment.GoldMainFragment;
import com.codeest.geeknews.ui.main.fragment.AboutFragment;
import com.codeest.geeknews.ui.main.fragment.LikeFragment;
import com.codeest.geeknews.ui.main.fragment.SettingFragment;
import com.codeest.geeknews.ui.vtex.fragment.VtexMainFragment;
import com.codeest.geeknews.ui.wechat.fragment.WechatMainFragment;
import com.codeest.geeknews.ui.zhihu.fragment.ZhihuMainFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 2017/2/26.
 */

@Module
public class PageModule {

    @Singleton
    @Provides
    ZhihuMainFragment provideZhihuMain() {
        return new ZhihuMainFragment();
    }

    @Singleton
    @Provides
    GankMainFragment provideGankMain() {
        return new GankMainFragment();
    }

    @Singleton
    @Provides
    WechatMainFragment provideWechatMain() {
        return new WechatMainFragment();
    }

    @Singleton
    @Provides
    GoldMainFragment provideGoldMain() {
        return new GoldMainFragment();
    }

    @Singleton
    @Provides
    VtexMainFragment provideVtexMain() {
        return new VtexMainFragment();
    }

    @Singleton
    @Provides
    LikeFragment provideLikeMain() {
        return new LikeFragment();
    }

    @Singleton
    @Provides
    SettingFragment provideSettingMain() {
        return new SettingFragment();
    }

    @Singleton
    @Provides
    AboutFragment provideAboutMain() {
        return new AboutFragment();
    }
}
