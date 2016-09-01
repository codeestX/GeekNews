package com.codeest.geeknews.model.http;

import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.model.bean.GankSearchItemBean;

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
    Observable<GankHttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankHttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<GankHttpResponse<List<GankItemBean>>> getRandomGirl(@Path("num") int num);

    /**
     * 搜索
     */
    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    Observable<GankHttpResponse<List<GankSearchItemBean>>> getSearchList(@Path("query") String query,@Path("type") String type,@Path("count") int num,@Path("page") int page);

}
