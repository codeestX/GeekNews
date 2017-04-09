package com.codeest.geeknews.presenter.contract;

import com.codeest.geeknews.base.BasePresenter;
import com.codeest.geeknews.base.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by codeest on 16/8/9.
 */

public interface MainContract {

    interface View extends BaseView{

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    interface  Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);
    }
}
