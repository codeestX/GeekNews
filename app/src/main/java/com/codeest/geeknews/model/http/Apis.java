package com.codeest.geeknews.model.http;

import com.codeest.geeknews.bean.UserCardInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by codeest on 2016/8/2.
 */
public interface Apis {

    String HOST = "http://dudu.ztgame.com/mobile/live/";

    @GET("getUserCard")
    Observable<HttpResponse<UserCardInfo>> getUserCardInfo(@Query("uid") int uid, @Query("token") String token, @Query("otherId") int otherId);
}
