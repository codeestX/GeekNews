package com.codeest.geeknews.model.http;

import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.model.bean.WelcomeBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by codeest on 16/8/19.
 */

public interface GankApis {

    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<HttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<HttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num,@Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<HttpResponse<List<GankItemBean>>> getRandomGirl(@Path("num") int num);

}
