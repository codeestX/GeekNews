package com.codeest.geeknews.component;

import com.umeng.analytics.MobclickAgent;
import com.codeest.geeknews.app.App;

import java.util.HashMap;

/**
 * Created by codeest on 2016/8/3.
 */
public class Umeng {

    public static void onEvent(String ev) {
        MobclickAgent.onEvent(App.getInstance(), ev);
    }

    public static void onEvent(String ev, String key, String value) {
        HashMap<String,String> m = new HashMap<String,String>();
        m.put(key, value);
        MobclickAgent.onEvent(App.getInstance(), ev, m);
    }
}
