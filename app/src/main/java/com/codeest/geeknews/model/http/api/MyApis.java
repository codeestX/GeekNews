package com.codeest.geeknews.model.http.api;

import com.codeest.geeknews.model.bean.VersionBean;
import com.codeest.geeknews.model.http.response.MyHttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by codeest on 16/10/10.
 * https://github.com/codeestX/my-restful-api
 */

public interface MyApis {

    String HOST = "http://codeest.me/api/geeknews/";

    String APK_DOWNLOAD_URL = "http://codeest.me/apk/geeknews.apk";

    /**
     * 获取最新版本信息
     * @return
     */
    @GET("version")
    Flowable<MyHttpResponse<VersionBean>> getVersionInfo();

}
