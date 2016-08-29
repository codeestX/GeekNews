package com.codeest.geeknews.app;

import android.os.Environment;

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

    public static final int TYPE_WECHAT = 106;

    public static final String APIKEY = "f95283476506aa756559dd28a56f0c0b"; //需要APIKEY请去 http://apistore.baidu.com/ 申请,复用会减少访问可用次数

    public static final String DATA_PATH = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";
}
