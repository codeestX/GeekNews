package com.codeest.geeknews.di.module;

import com.codeest.geeknews.BuildConfig;
import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.di.qualifier.GankUrl;
import com.codeest.geeknews.di.qualifier.GoldUrl;
import com.codeest.geeknews.di.qualifier.MyUrl;
import com.codeest.geeknews.di.qualifier.VtexUrl;
import com.codeest.geeknews.di.qualifier.WechatUrl;
import com.codeest.geeknews.di.qualifier.ZhihuUrl;
import com.codeest.geeknews.model.http.api.GankApis;
import com.codeest.geeknews.model.http.api.GoldApis;
import com.codeest.geeknews.model.http.api.MyApis;
import com.codeest.geeknews.model.http.api.VtexApis;
import com.codeest.geeknews.model.http.api.WeChatApis;
import com.codeest.geeknews.model.http.api.ZhihuApis;
import com.codeest.geeknews.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codeest on 2017/2/26.
 */

@Module
public class HttpModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhihuApis.HOST);
    }

    @Singleton
    @Provides
    @WechatUrl
    Retrofit provideWechatRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, WeChatApis.HOST);
    }

    @Singleton
    @Provides
    @GankUrl
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApis.HOST);
    }

    @Singleton
    @Provides
    @GoldUrl
    Retrofit provideGoldRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GoldApis.HOST);
    }

    @Singleton
    @Provides
    @VtexUrl
    Retrofit provideVtexRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, VtexApis.HOST);
    }

    @Singleton
    @Provides
    @MyUrl
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, MyApis.HOST);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
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
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        }
//        设置统一的请求头部参数
//        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @Singleton
    @Provides
    ZhihuApis provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        return retrofit.create(ZhihuApis.class);
    }

    @Singleton
    @Provides
    GankApis provideGankService(@GankUrl  Retrofit retrofit) {
        return retrofit.create(GankApis.class);
    }

    @Singleton
    @Provides
    WeChatApis provideWechatService(@WechatUrl Retrofit retrofit) {
        return retrofit.create(WeChatApis.class);
    }

    @Singleton
    @Provides
    GoldApis provideGoldService(@GoldUrl Retrofit retrofit) {
        return retrofit.create(GoldApis.class);
    }

    @Singleton
    @Provides
    VtexApis provideVtexService(@VtexUrl Retrofit retrofit) {
        return retrofit.create(VtexApis.class);
    }

    @Singleton
    @Provides
    MyApis provideMyService(@MyUrl Retrofit retrofit) {
        return retrofit.create(MyApis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
