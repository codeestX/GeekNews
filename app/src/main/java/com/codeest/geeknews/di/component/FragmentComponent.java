package com.codeest.geeknews.di.component;

import android.app.Activity;

import com.codeest.geeknews.di.FragmentScope;
import com.codeest.geeknews.di.module.FragmentModule;
import com.codeest.geeknews.ui.gank.fragment.TechFragment;
import com.codeest.geeknews.ui.gank.fragment.GankMainFragment;
import com.codeest.geeknews.ui.gank.fragment.GirlFragment;
import com.codeest.geeknews.ui.main.fragment.LikeFragment;
import com.codeest.geeknews.ui.wechat.fragment.WechatMainFragment;
import com.codeest.geeknews.ui.zhihu.fragment.CommentFragment;
import com.codeest.geeknews.ui.zhihu.fragment.DailyFragment;
import com.codeest.geeknews.ui.zhihu.fragment.HotFragment;
import com.codeest.geeknews.ui.zhihu.fragment.SectionFragment;
import com.codeest.geeknews.ui.zhihu.fragment.ZhihuMainFragment;
import com.codeest.geeknews.ui.zhihu.fragment.ThemeFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DailyFragment dailyFragment);

    void inject(ThemeFragment themeFragment);

    void inject(SectionFragment sectionFragment);

    void inject(HotFragment hotFragment);

    void inject(CommentFragment longCommentFragment);

    void inject(TechFragment techFragment);

    void inject(GirlFragment girlFragment);

    void inject(LikeFragment likeFragment);

    void inject(WechatMainFragment wechatMainFragment);
}
