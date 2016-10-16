package com.codeest.geeknews.model.http;

import com.codeest.geeknews.model.bean.VersionBean;

import retrofit2.http.GET;
import rx.Observable;

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
    Observable<MyHttpResponse<VersionBean>> getVersionInfo();

}
