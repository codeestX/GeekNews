package com.codeest.geeknews.model.bean;

/**
 * Created by codeest on 16/12/23.
 */

public class TopicListBean {

    private String imgUrl;

    private String name;

    private String updateTime;

    private String lastUser;

    private String node;

    private int commentNum;

    private String title;

    private String topicId;

    public int getCommentNum() {
        return commentNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getLastUser() {
        return lastUser;
    }

    public String getName() {
        return name;
    }

    public String getNode() {
        return node;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
