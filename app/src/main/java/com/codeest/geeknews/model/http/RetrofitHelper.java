package com.codeest.geeknews.model.http;

import com.codeest.geeknews.BuildConfig;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.model.bean.CommentBean;
import com.codeest.geeknews.model.bean.DailyBeforeListBean;
import com.codeest.geeknews.model.bean.DailyListBean;
import com.codeest.geeknews.model.bean.DetailExtraBean;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.model.bean.ThemeListBean;
import com.codeest.geeknews.model.bean.WelcomeBean;
import com.codeest.geeknews.model.bean.ZhihuDetailBean;
import com.codeest.geeknews.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;
    private static ZhihuApis zhihuApiService = null;
    private static GankApis gankApiService = null;

    private void init() {
        initOkHttp();
        zhihuApiService = getZhihuApiService();
        gankApiService = getGankApiService();
    }

    public RetrofitHelper() {
        init();
    }

//    public static RetrofitHelper getInstance() {
//        return RetrofitHelperHolder.INSTANCE;
//    }
//
//    private static class RetrofitHelperHolder {
//        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
//    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        // 缓存 http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(Constants.DATA_PATH,"/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private static ZhihuApis getZhihuApiService() {
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return zhihuRetrofit.create(ZhihuApis.class);
    }

    private static GankApis getGankApiService() {
        Retrofit gankRetrofit = new Retrofit.Builder()
                .baseUrl(GankApis.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return gankRetrofit.create(GankApis.class);
    }

    public Observable<DailyListBean> fetchDailyListInfo() {
        return zhihuApiService.getDailyList();
    }

    public Observable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return zhihuApiService.getDailyBeforeList(date);
    }

    public Observable<ThemeListBean> fetchDailyThemeListInfo() {
        return zhihuApiService.getThemeList();
    }

    public Observable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return zhihuApiService.getDetailInfo(id);
    }

    public Observable<DetailExtraBean> fetchDetailExtraInfo(int id) {
        return zhihuApiService.getDetailExtraInfo(id);
    }

    public Observable<WelcomeBean> fetchWelcomeInfo(String res) {
        return zhihuApiService.getWelcomeInfo(res);
    }

    public Observable<CommentBean> fetchLongCommentInfo(int id) {
        return zhihuApiService.getLongCommentInfo(id);
    }

    public Observable<CommentBean> fetchShortCommentInfo(int id) {
        return zhihuApiService.getShortCommentInfo(id);
    }

    public Observable<HttpResponse<List<GankItemBean>>> fetchTechList(String tech,int num,int page) {
        return gankApiService.getTechList(tech, num, page);
    }

    public Observable<HttpResponse<List<GankItemBean>>> fetchGirlList(int num,int page) {
        return gankApiService.getGirlList(num, page);
    }

    public Observable<HttpResponse<List<GankItemBean>>> fetchRandomGirl(int num) {
        return gankApiService.getRandomGirl(num);
    }

}
