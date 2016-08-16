package com.codeest.geeknews.di.component;

import android.app.Activity;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.di.ActivityScope;
import com.codeest.geeknews.di.module.ActivityModule;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.ui.main.MainActivity;
import com.codeest.geeknews.ui.main.WelcomeActivity;
import com.codeest.geeknews.ui.zhihu.activity.ZhihuDetailActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

    void inject(ZhihuDetailActivity zhihuDetailActivity);

}
