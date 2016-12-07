package com.codeest.geeknews.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.app.Constants;

/**
 * Created by codeest on 16/8/31.
 */

public class SharedPreferenceUtil {

    private static final boolean DEFAULT_NIGHT_MODE = false;
    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;

    private static final boolean DEFAULT_LIKE_POINT = false;
    private static final boolean DEFAULT_VERSION_POINT = false;
    private static final boolean DEFAULT_MANAGER_POINT = false;

    private static final int DEFAULT_CURRENT_ITEM = Constants.TYPE_ZHIHU;

    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getNightModeState() {
        return getAppSp().getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    public static void setNightModeState(boolean state) {
        getAppSp().edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    public static boolean getNoImageState() {
        return getAppSp().getBoolean(Constants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    public static void setNoImageState(boolean state) {
        getAppSp().edit().putBoolean(Constants.SP_NO_IMAGE, state).apply();
    }

    public static boolean getAutoCacheState() {
        return getAppSp().getBoolean(Constants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
    }

    public static void setAutoCacheState(boolean state) {
        getAppSp().edit().putBoolean(Constants.SP_AUTO_CACHE, state).apply();
    }

    public static int getCurrentItem() {
        return getAppSp().getInt(Constants.SP_CURRENT_ITEM, DEFAULT_CURRENT_ITEM);
    }

    public static void setCurrentItem(int item) {
        getAppSp().edit().putInt(Constants.SP_CURRENT_ITEM, item).apply();
    }

    public static boolean getLikePoint() {
        return getAppSp().getBoolean(Constants.SP_LIKE_POINT, DEFAULT_LIKE_POINT);
    }

    public static void setLikePoint(boolean isFirst) {
        getAppSp().edit().putBoolean(Constants.SP_LIKE_POINT, isFirst).apply();
    }

    public static boolean getVersionPoint() {
        return getAppSp().getBoolean(Constants.SP_VERSION_POINT, DEFAULT_VERSION_POINT);
    }

    public static void setVersionPoint(boolean isFirst) {
        getAppSp().edit().putBoolean(Constants.SP_VERSION_POINT, isFirst).apply();
    }

    public static boolean getManagerPoint() {
        return getAppSp().getBoolean(Constants.SP_MANAGER_POINT, DEFAULT_MANAGER_POINT);
    }

    public static void setManagerPoint(boolean isFirst) {
        getAppSp().edit().putBoolean(Constants.SP_MANAGER_POINT, isFirst).apply();
    }
}
