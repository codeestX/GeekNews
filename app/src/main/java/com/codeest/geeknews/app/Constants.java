package com.codeest.geeknews.app;

import android.os.Environment;

import com.codeest.geeknews.di.component.AppComponent;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {

    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final String DATA_PATH = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";
}
