package com.codeest.geeknews.model.http;

import com.codeest.geeknews.app.Constants;
import com.codeest.geeknews.model.bean.CommentBean;
import com.codeest.geeknews.model.bean.DailyBeforeListBean;
import com.codeest.geeknews.model.bean.DailyListBean;
import com.codeest.geeknews.model.bean.DetailExtraBean;
import com.codeest.geeknews.model.bean.GankItemBean;
import com.codeest.geeknews.model.bean.GankSearchItemBean;
import com.codeest.geeknews.model.bean.GoldListBean;
import com.codeest.geeknews.model.bean.HotListBean;
import com.codeest.geeknews.model.bean.NodeBean;
import com.codeest.geeknews.model.bean.NodeListBean;
import com.codeest.geeknews.model.bean.RepliesListBean;
import com.codeest.geeknews.model.bean.SectionChildListBean;
import com.codeest.geeknews.model.bean.SectionListBean;
import com.codeest.geeknews.model.bean.ThemeChildListBean;
import com.codeest.geeknews.model.bean.ThemeListBean;
import com.codeest.geeknews.model.bean.VersionBean;
import com.codeest.geeknews.model.bean.WXItemBean;
import com.codeest.geeknews.model.bean.WelcomeBean;
import com.codeest.geeknews.model.bean.ZhihuDetailBean;
import com.codeest.geeknews.model.http.api.GankApis;
import com.codeest.geeknews.model.http.api.GoldApis;
import com.codeest.geeknews.model.http.api.MyApis;
import com.codeest.geeknews.model.http.api.VtexApis;
import com.codeest.geeknews.model.http.api.WeChatApis;
import com.codeest.geeknews.model.http.api.ZhihuApis;
import com.codeest.geeknews.model.http.response.GankHttpResponse;
import com.codeest.geeknews.model.http.response.GoldHttpResponse;
import com.codeest.geeknews.model.http.response.MyHttpResponse;
import com.codeest.geeknews.model.http.response.WXHttpResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper {

    private ZhihuApis mZhihuApiService;
    private GankApis mGankApiService;
    private WeChatApis mWechatApiService;
    private MyApis mMyApiService;
    private GoldApis mGoldApiService;
    private VtexApis mVtexApiService;

    public RetrofitHelper(ZhihuApis zhihuApiService, GankApis gankApiService, WeChatApis wechatApiService,
                          MyApis myApiService, GoldApis goldApiService, VtexApis vtexApiService) {
        this.mZhihuApiService = zhihuApiService;
        this.mGankApiService = gankApiService;
        this.mWechatApiService = wechatApiService;
        this.mMyApiService = myApiService;
        this.mGoldApiService = goldApiService;
        this.mVtexApiService = vtexApiService;
    }

    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mZhihuApiService.getDailyList();
    }

    public Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return mZhihuApiService.getDailyBeforeList(date);
    }

    public Flowable<ThemeListBean> fetchDailyThemeListInfo() {
        return mZhihuApiService.getThemeList();
    }

    public Flowable<ThemeChildListBean> fetchThemeChildListInfo(int id) {
        return mZhihuApiService.getThemeChildList(id);
    }

    public Flowable<SectionListBean> fetchSectionListInfo() {
        return mZhihuApiService.getSectionList();
    }

    public Flowable<SectionChildListBean> fetchSectionChildListInfo(int id) {
        return mZhihuApiService.getSectionChildList(id);
    }

    public Flowable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mZhihuApiService.getDetailInfo(id);
    }

    public Flowable<DetailExtraBean> fetchDetailExtraInfo(int id) {
        return mZhihuApiService.getDetailExtraInfo(id);
    }

    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    public Flowable<CommentBean> fetchLongCommentInfo(int id) {
        return mZhihuApiService.getLongCommentInfo(id);
    }

    public Flowable<CommentBean> fetchShortCommentInfo(int id) {
        return mZhihuApiService.getShortCommentInfo(id);
    }

    public Flowable<HotListBean> fetchHotListInfo() {
        return mZhihuApiService.getHotList();
    }

    public Flowable<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page) {
        return mGankApiService.getTechList(tech, num, page);
    }

    public Flowable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return mGankApiService.getGirlList(num, page);
    }

    public Flowable<GankHttpResponse<List<GankItemBean>>> fetchRandomGirl(int num) {
        return mGankApiService.getRandomGirl(num);
    }

    public Flowable<GankHttpResponse<List<GankSearchItemBean>>> fetchGankSearchList(String query,String type,int num,int page) {
        return mGankApiService.getSearchList(query,type,num,page);
    }

    public Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page) {
        return mWechatApiService.getWXHot(Constants.KEY_API, num, page);
    }

    public Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatSearchListInfo(int num, int page, String word) {
        return mWechatApiService.getWXHotSearch(Constants.KEY_API, num, page, word);
    }

    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return mMyApiService.getVersionInfo();
    }

    public Flowable<GoldHttpResponse<List<GoldListBean>>> fetchGoldList(String type, int num, int page) {
        return mGoldApiService.getGoldList(Constants.LEANCLOUD_ID, Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\"}", "-createdAt", "user,user.installation", num, page * num);
    }

    public Flowable<GoldHttpResponse<List<GoldListBean>>> fetchGoldHotList(String type, String dataTime, int limit) {
        return mGoldApiService.getGoldHot(Constants.LEANCLOUD_ID, Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\",\"createdAt\":{\"$gt\":{\"__type\":\"Date\",\"iso\":\"" + dataTime + "T00:00:00.000Z\"}},\"objectId\":{\"$nin\":[\"58362f160ce463005890753e\",\"583659fcc59e0d005775cc8c\",\"5836b7358ac2470065d3df62\"]}}",
                "-hotIndex", "user,user.installation", limit);
    }

    public Flowable<NodeBean> fetchNodeInfo(String name) {
        return mVtexApiService.getNodeInfo(name);
    }

    public Flowable<List<NodeListBean>> fetchTopicList(String name) {
        return mVtexApiService.getTopicList(name);
    }

    public Flowable<List<NodeListBean>> fetchTopicInfo(String id) {
        return mVtexApiService.getTopicInfo(id);
    }

    public Flowable<List<RepliesListBean>> fetchRepliesList(String id){
        return mVtexApiService.getRepliesList(id);
    }
}
