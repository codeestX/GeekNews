package com.codeest.geeknews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.codeest.geeknews.di.component.AppComponent;
import com.codeest.geeknews.di.component.DaggerAppComponent;
import com.codeest.geeknews.di.module.AppModule;
import com.codeest.geeknews.util.SystemUtil;
import com.codeest.geeknews.widget.AppBlockCanaryContext;
import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashSet;
import java.util.Set;

import static com.codeest.geeknews.util.LogUtil.isDebug;

/**
 * Created by codeest on 2016/8/2.
 */
public class App extends Application{

    private static App instance;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        getScreenSize();

        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();

        //初始化错误收集
//        CrashHandler.init(new CrashHandler(getApplicationContext()));
        initBugly();

        //初始化内存泄漏检测
        LeakCanary.install(this);

        //初始化过度绘制检测
        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        //初始化tbs x5 webview
        QbSdk.allowThirdPartyAppDownload(true);
        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
            }
        });
    }

    private void initBugly() {
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, Constants.BUGLY_ID, isDebug, strategy);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
}
