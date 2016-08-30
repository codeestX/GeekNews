package com.codeest.geeknews.model.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.util.LogUtil;
import com.google.gson.Gson;

/**
 * Created by codeest on 2016/8/30.
 */
public class PreferenceHelper {

    SharedPreferences mSharedPreferences;
    SharedPreferences mJsonSharedPreferences;

    public static final String SHAREDPREFERENCES_NAME = "my_sp";
    public static final String SHAREDPREFERENCES_JSON_NAME = "my_json_sp";
    public static final String SHAREDPREFERENCES_JSON_PATH = Constants.PATH_DATA;
    ///data/data/YOUR_PACKAGE_NAME/shared_prefs/YOUR_PREFS_NAME.xml

    public static final boolean DEFAULT_NIGHT_MODE = false;
    public static final boolean DEFAULT_NO_IMAGE = false;
    public static final boolean DEFAULT_AUTO_SAVE = true;

    public PreferenceHelper(Context mContext) {
        mSharedPreferences = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        mJsonSharedPreferences = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_JSON_NAME, Context.MODE_PRIVATE);
    }

    public void saveJsonToSharedPreferences(String key, String json) {
        mSharedPreferences.edit().putString(key, json).apply();
    }

    public static <T> T loadJsonFromSharedPreferences(SharedPreferences sp, String key, Class<T> cls) {
        String json = sp.getString(key, "");
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, cls);
            } catch (Exception e) {
                LogUtil.e(e);
            }
        }
        return null;
    }

    public boolean getNightModeState() {
        return mSharedPreferences.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    public void setNightModeState(boolean state) {
        mSharedPreferences.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    public boolean getNoImageState() {
        return mSharedPreferences.getBoolean(Constants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    public void setNoImageState(boolean state) {
        mSharedPreferences.edit().putBoolean(Constants.SP_NO_IMAGE, state).apply();
    }

    public boolean getAutoCacheState() {
        return mSharedPreferences.getBoolean(Constants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
    }

    public void setAutoCacheState(boolean state) {
        mSharedPreferences.edit().putBoolean(Constants.SP_AUTO_CACHE, state).apply();
    }

    public int getCacheSize() {
        return mJsonSharedPreferences.getAll().size();
    }

    public void clearCache() {
        mJsonSharedPreferences.getAll().clear();
    }
}
