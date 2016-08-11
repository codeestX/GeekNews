package com.codeest.geeknews.model.bean;

/**
 * Created by codeest on 2016/8/3.
 */
public class UserCardInfo {

    /**
     * userId : 21001043
     * nick : 21001043
     * sign : 主播很懒，什么都没留下
     * followNum : 0
     * myFansNum : 3
     * faceUrl : http://dudu.ztgame.com/headimg/21001043
     * isFollow : 0
     * sex : 1
     */

    private int userId;
    private String nick;
    private String sign;
    private int followNum;
    private int myFansNum;
    private String faceUrl;
    private int isFollow;
    private int sex;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getMyFansNum() {
        return myFansNum;
    }

    public void setMyFansNum(int myFansNum) {
        this.myFansNum = myFansNum;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
