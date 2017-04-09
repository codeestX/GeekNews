package com.codeest.geeknews.di.component;

import android.app.Activity;

import com.codeest.geeknews.di.scope.ActivityScope;
import com.codeest.geeknews.di.module.ActivityModule;
import com.codeest.geeknews.ui.main.activity.MainActivity;
import com.codeest.geeknews.ui.main.activity.WelcomeActivity;
import com.codeest.geeknews.ui.vtex.activity.NodeListActivity;
import com.codeest.geeknews.ui.vtex.activity.RepliesActivity;
import com.codeest.geeknews.ui.zhihu.activity.SectionActivity;
import com.codeest.geeknews.ui.zhihu.activity.ThemeActivity;
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

    void inject(ThemeActivity themeActivity);

    void inject(SectionActivity sectionActivity);

    void inject(RepliesActivity repliesActivity);

    void inject(NodeListActivity nodeListActivity);
}
