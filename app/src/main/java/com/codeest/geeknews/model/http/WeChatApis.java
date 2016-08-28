package com.codeest.geeknews.model.http;

import com.codeest.geeknews.model.bean.WXItemBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by codeest on 16/8/28.
 */

public interface WeChatApis {

    String HOST = "http://apis.baidu.com/txapi/weixin/";

    /**
     * 微信精选列表
     */
    @GET("wxhot")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("num") int num, @Query("page") int page);
}
