package com.codeest.geeknews.model.http;

import com.codeest.geeknews.model.bean.DailyBeforeListBean;
import com.codeest.geeknews.model.bean.DailyListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by codeest on 2016/8/2.
 * 知乎APIs
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */
    @GET("stories/latest")
    Observable<DailyListBean> getDailyList();

    /**
     * 往期日报
     */
    @GET("stories/before/{date}")
    Observable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

//    /**
//     * 获取日报详情数据
//     *
//     * @param id
//     * @return
//     */
//    @GET("story/{id}")
//    Observable<DailyDetail> getNewsDetails(@Path("id") int id);
//
//    /**
//     * 根据分辨率获取启动界面图片
//     *
//     * @param res
//     * @return
//     */
//    @GET("start-image/{res}")
//    Observable<LuanchImageBean> getLuanchImage(@Path("res") String res);
//
//    /**
//     * 获取专题日报
//     *
//     * @return
//     */
//    @GET("themes")
//    Observable<DailyTypeBean> getDailyType();
//
//    /**
//     * 根据id查询主题日报内容
//     *
//     * @param id
//     * @return
//     */
//    @GET("theme/{id}")
//    Observable<ThemesDetails> getThemesDetailsById(@Path("id") int id);
//
//    /**
//     * 根据id查询日报的额外信息
//     *
//     * @param id
//     * @return
//     */
//    @GET("story-extra/{id}")
//    Observable<DailyExtraMessage> getDailyExtraMessageById(@Path("id") int id);
//
//    /**
//     * 根据id查询日报的长评论
//     *
//     * @param id
//     * @return
//     */
//    @GET("story/{id}/long-comments")
//    Observable<DailyComment> getDailyLongComment(@Path("id") int id);
//
//    /**
//     * 根据id查询日报的短评论
//     *
//     * @param id
//     * @return
//     */
//    @GET("story/{id}/short-comments")
//    Observable<DailyComment> getDailyShortComment(@Path("id") int id);
//
//    /**
//     * 获取最新的热门新闻
//     *
//     * @return
//     */
//    @GET("news/hot")
//    Observable<HotNews> getHotNews();
//
//
//    /**
//     * 获取知乎专栏数据
//     *
//     * @return
//     */
//    @GET("sections")
//    Observable<DailySections> getZhiHuSections();
//
//    /**
//     * 获取专栏详情数据
//     *
//     * @param id
//     * @return
//     */
//    @GET("section/{id}")
//    Observable<SectionsDetails> getSectionsDetails(@Path("id") int id);
//
//    /**
//     * 获取专栏的之前消息
//     *
//     * @param id
//     * @param timestamp
//     * @return
//     */
//    @GET("section/{id}/before/{timestamp}")
//    Observable<SectionsDetails> getBeforeSectionsDetails(@Path("id") int id, @Path("timestamp") long timestamp);
//
//    /**
//     * 根据日报id查询该日报的推荐者信息
//     *
//     * @param id
//     * @return
//     */
//    @GET("story/{id}/recommenders")
//    Observable<DailyRecommend> getDailyRecommendEditors(@Path("id") int id);
}
