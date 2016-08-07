package com.codeest.geeknews.di.component;

import android.content.Context;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.di.ContextLife;
import com.codeest.geeknews.di.module.AppModule;
import com.codeest.geeknews.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类

}
