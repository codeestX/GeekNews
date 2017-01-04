package com.codeest.geeknews.model.bean;

/**
 * Created by codeest on 16/12/22.
 */

public class RepliesListBean {


    /**
     * id : 3876167
     * thanks : 0
     * content : Android-x86
     * content_rendered : Android-x86
     * member : {"id":68632,"username":"mason961125","tagline":"","avatar_normal":"//cdn.v2ex.co/gravatar/749c43cb8cd7c0786a55d4be05cc6b3a?s=24&d=retro","avatar_normal":"//cdn.v2ex.co/gravatar/749c43cb8cd7c0786a55d4be05cc6b3a?s=48&d=retro","avatar_large":"//cdn.v2ex.co/gravatar/749c43cb8cd7c0786a55d4be05cc6b3a?s=73&d=retro"}
     * created : 1482323170
     * last_modified : 1482323170
     */

    private int thanks;
    private String content_rendered;
    private MemberBean member;
    private int created;

    public int getThanks() {
        return thanks;
    }

    public void setThanks(int thanks) {
        this.thanks = thanks;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public static class MemberBean {
        /**
         * id : 68632
         * username : mason961125
         * tagline :
         * avatar_normal : //cdn.v2ex.co/gravatar/749c43cb8cd7c0786a55d4be05cc6b3a?s=24&d=retro
         * avatar_normal : //cdn.v2ex.co/gravatar/749c43cb8cd7c0786a55d4be05cc6b3a?s=48&d=retro
         * avatar_large : //cdn.v2ex.co/gravatar/749c43cb8cd7c0786a55d4be05cc6b3a?s=73&d=retro
         */

        private String username;
        private String avatar_normal;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getavatar_normal() {
            return avatar_normal;
        }

        public void setavatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }
    }
}
