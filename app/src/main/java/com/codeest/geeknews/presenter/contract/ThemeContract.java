package com.codeest.geeknews.presenter.contract;

import com.codeest.geeknews.base.BasePresenter;
import com.codeest.geeknews.base.BaseView;
import com.codeest.geeknews.model.bean.ThemeListBean;

/**
 * Created by codeest on 16/8/12.
 */

public interface ThemeContract {

    interface View extends BaseView {

        void showContent(ThemeListBean themeListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getThemeData();
    }
}
