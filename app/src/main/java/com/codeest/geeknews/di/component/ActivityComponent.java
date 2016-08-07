package com.codeest.geeknews.di.component;

import android.app.Activity;

import com.codeest.geeknews.di.ActivityScope;
import com.codeest.geeknews.di.module.ActivityModule;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    RetrofitHelper getRetrofitHelper();

    Activity getActivity();

    void inject(LoginActivity loginActivity);

}
