package com.codeest.geeknews.app;

import android.os.Environment;

import com.codeest.geeknews.di.component.AppComponent;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {

    public static String DATA_PATH = App.getInstance().getCacheDir().getAbsolutePath();

    public static String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ztgame" + File.separator + "starshow";
}
