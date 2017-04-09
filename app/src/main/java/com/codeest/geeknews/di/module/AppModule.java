package com.codeest.geeknews.di.module;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.model.http.api.GankApis;
import com.codeest.geeknews.model.http.api.GoldApis;
import com.codeest.geeknews.model.http.api.MyApis;
import com.codeest.geeknews.model.http.api.VtexApis;
import com.codeest.geeknews.model.http.api.WeChatApis;
import com.codeest.geeknews.model.http.api.ZhihuApis;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(ZhihuApis zhihuApiService, GankApis gankApiService, WeChatApis wechatApiService,
                                         MyApis myApiService, GoldApis goldApiService, VtexApis vtexApiService) {
        return new RetrofitHelper(zhihuApiService, gankApiService, wechatApiService,
                myApiService, goldApiService, vtexApiService);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper(application);
    }
}
