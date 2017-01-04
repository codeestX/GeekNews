package com.codeest.geeknews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by codeest on 16/12/22.
 */

public class NodeListBean implements Parcelable{

    /**
     * id : 328643
     * title : django 的视图函数执行是同步的还是异步的？
     * url : http://www.v2ex.com/t/328643
     * content : 比如两个用户同时访问一个 url ， django 后台会调用相应的视图函数处理。那么这个处理过程是先完成第一个用户的 request-response 周期后再执行第二个用户的请求呢？还是同时执行两个用户的 request 请求？
     * content_rendered : 比如两个用户同时访问一个 url ， django 后台会调用相应的视图函数处理。那么这个处理过程是先完成第一个用户的 request-response 周期后再执行第二个用户的请求呢？还是同时执行两个用户的 request 请求？
     * replies : 4
     * member : {"id":206547,"username":"chuanqirenwu","tagline":"None","avatar_normal":"//cdn.v2ex.co/gravatar/a1ceb1d16d738ce96a1f8dff4c2ed803?s=24&d=retro","avatar_normal":"//cdn.v2ex.co/gravatar/a1ceb1d16d738ce96a1f8dff4c2ed803?s=48&d=retro","avatar_large":"//cdn.v2ex.co/gravatar/a1ceb1d16d738ce96a1f8dff4c2ed803?s=73&d=retro"}
     * node : {"id":90,"name":"python","title":"Python","title_alternative":"Python","url":"http://www.v2ex.com/go/python","topics":6163,"avatar_normal":"//cdn.v2ex.co/navatar/8613/985e/90_mini.png?m=1481194344","avatar_normal":"//cdn.v2ex.co/navatar/8613/985e/90_normal.png?m=1481194344","avatar_large":"//cdn.v2ex.co/navatar/8613/985e/90_large.png?m=1481194344"}
     * created : 1482132218
     * last_modified : 1482132218
     * last_touched : 1482078362
     */

    private String id;
    private String title;
    private String content_rendered;
    private int replies;
    private MemberBean member;
    private NodeBean node;
    private int created;
    private int last_modified;

    public static class MemberBean implements Parcelable{
        /**
         * id : 206547
         * username : chuanqirenwu
         * tagline : None
         * avatar_normal : //cdn.v2ex.co/gravatar/a1ceb1d16d738ce96a1f8dff4c2ed803?s=24&d=retro
         * avatar_normal : //cdn.v2ex.co/gravatar/a1ceb1d16d738ce96a1f8dff4c2ed803?s=48&d=retro
         * avatar_large : //cdn.v2ex.co/gravatar/a1ceb1d16d738ce96a1f8dff4c2ed803?s=73&d=retro
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.username);
            dest.writeString(this.avatar_normal);
        }

        public MemberBean() {
        }

        protected MemberBean(Parcel in) {
            this.username = in.readString();
            this.avatar_normal = in.readString();
        }

        public static final Creator<MemberBean> CREATOR = new Creator<MemberBean>() {
            @Override
            public MemberBean createFromParcel(Parcel source) {
                return new MemberBean(source);
            }

            @Override
            public MemberBean[] newArray(int size) {
                return new MemberBean[size];
            }
        };
    }

    public static class NodeBean implements Parcelable{

        private String title;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
        }

        public NodeBean() {
        }

        protected NodeBean(Parcel in) {
            this.title = in.readString();
        }

        public static final Creator<NodeBean> CREATOR = new Creator<NodeBean>() {
            @Override
            public NodeBean createFromParcel(Parcel source) {
                return new NodeBean(source);
            }

            @Override
            public NodeBean[] newArray(int size) {
                return new NodeBean[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public String getTitle() {
        return title;
    }

    public int getCreated() {
        return created;
    }

    public String getId() {
        return id;
    }

    public int getLast_modified() {
        return last_modified;
    }

    public int getReplies() {
        return replies;
    }

    public MemberBean getMember() {
        return member;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLast_modified(int last_modified) {
        this.last_modified = last_modified;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public NodeBean getNode() {
        return node;
    }

    public void setNode(NodeBean node) {
        this.node = node;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content_rendered);
        dest.writeInt(this.replies);
        dest.writeParcelable(this.member, flags);
        dest.writeParcelable(this.node, flags);
        dest.writeInt(this.created);
        dest.writeInt(this.last_modified);
    }

    public NodeListBean() {
    }

    protected NodeListBean(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.content_rendered = in.readString();
        this.replies = in.readInt();
        this.member = in.readParcelable(MemberBean.class.getClassLoader());
        this.node = in.readParcelable(NodeBean.class.getClassLoader());
        this.created = in.readInt();
        this.last_modified = in.readInt();
    }

    public static final Creator<NodeListBean> CREATOR = new Creator<NodeListBean>() {
        @Override
        public NodeListBean createFromParcel(Parcel source) {
            return new NodeListBean(source);
        }

        @Override
        public NodeListBean[] newArray(int size) {
            return new NodeListBean[size];
        }
    };
}
