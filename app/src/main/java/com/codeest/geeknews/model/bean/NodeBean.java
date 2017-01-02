package com.codeest.geeknews.model.bean;

/**
 * Created by codeest on 16/12/22.
 */

public class NodeBean {


    /**
     * id : 300
     * name : programmer
     * url : http://www.v2ex.com/go/programmer
     * title : 程序员
     * title_alternative : Programmer
     * topics : 14214
     * stars : 2806
     * header : While code monkeys are not eating bananas, they're coding.
     * footer : null
     * created : 1293396163
     * avatar_normal : //cdn.v2ex.co/navatar/94f6/d7e0/300_mini.png?m=1482295939
     * avatar_normal : //cdn.v2ex.co/navatar/94f6/d7e0/300_normal.png?m=1482295939
     * avatar_large : //cdn.v2ex.co/navatar/94f6/d7e0/300_large.png?m=1482295939
     */

    private String title;
    private int topics;
    private int stars;
    private String header;
    private String avatar_normal;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getavatar_normal() {
        return avatar_normal;
    }

    public void setavatar_normal(String avatar_normal) {
        this.avatar_normal = avatar_normal;
    }
}
