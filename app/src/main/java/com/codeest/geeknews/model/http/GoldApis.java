package com.codeest.geeknews.model.http;

import com.codeest.geeknews.model.bean.GoldListBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by codeest on 16/11/27.
 */

public interface GoldApis {

    String HOST = "https://api.leancloud.cn/";

    /**
     * 文章列表
     * https://api.leancloud.cn/1.1/classes/Entry?where={"category":"android"}&order=-createdAt&skip=0&include=user,user.installation&limit=20
     */
    @GET("1.1/classes/Entry")
    Observable<GoldHttpResponse<List<GoldListBean>>> getGoldList(@Query("where") String where,
                                                                  @Query("order") String order,
                                                                  @Query("include") String include,
                                                                  @Query("limit") int limit,
                                                                  @Query("skip") int skip);

    /**
     * 热门推荐
     * https://api.leancloud.cn/1.1/classes/Entry?include=user,user.installation&where={"category":"android","createdAt":{"$gt":{"__type":"Date","iso":"2016-11-25T16:04:44.024Z"}},"objectId":{"$nin":["58362f160ce463005890753e","583659fcc59e0d005775cc8c","5836b7358ac2470065d3df62"]}}&order=-hotIndex&limit=3
     */
    @GET("1.1/classes/Entry")
    Observable<GoldHttpResponse<List<GoldListBean>>> getGoldHot(@Query("where") String where,
                                                            @Query("order") String order,
                                                            @Query("include") String include,
                                                            @Query("limit") int limit);

}
