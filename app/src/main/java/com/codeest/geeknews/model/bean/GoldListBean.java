package com.codeest.geeknews.model.bean;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldListBean {

    private String objectId;

    private String createdAt;

    private String title;

    private int collectionCount;

    private int commentsCount;

    private String url;

    private GoldListUserBean user;

    private GoldListScreenshotBean screenshot;

    public static class GoldListUserBean {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class GoldListScreenshotBean {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public GoldListScreenshotBean getScreenshot() {
        return screenshot;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public GoldListUserBean getUser() {
        return user;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setScreenshot(GoldListScreenshotBean screenshot) {
        this.screenshot = screenshot;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUser(GoldListUserBean user) {
        this.user = user;
    }
}
