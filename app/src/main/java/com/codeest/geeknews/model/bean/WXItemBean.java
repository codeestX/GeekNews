package com.codeest.geeknews.model.bean;

/**
 * Created by codeest on 16/8/28.
 */

public class WXItemBean {

    /**
     * ctime : 2016-03-31
     * title : 小本生意做什么挣钱十七大小本生意推荐
     * description : 创业最前线
     * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225297.jpg/640
     * url : http://mp.weixin.qq.com/s?__biz=MzA3NjgzNDUwMQ==&idx=2&mid=401864059&sn=cfa082e38ba38c7e673b1ce0a075faee
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
