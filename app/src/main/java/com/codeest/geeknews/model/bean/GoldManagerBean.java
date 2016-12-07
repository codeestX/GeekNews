package com.codeest.geeknews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldManagerBean extends RealmObject implements Parcelable {

    public GoldManagerBean() {

    }

    private RealmList<GoldManagerItemBean> managerList;

    public RealmList<GoldManagerItemBean> getManagerList() {
        return managerList;
    }

    public void setManagerList(RealmList<GoldManagerItemBean> managerList) {
        this.managerList = managerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.managerList);
    }

    protected GoldManagerBean(Parcel in) {
        this.managerList = new RealmList<>();
        in.readList(this.managerList, GoldManagerItemBean.class.getClassLoader());
    }

    public GoldManagerBean(RealmList<GoldManagerItemBean> mList) {
        this.managerList = mList;
    }

    public static final Parcelable.Creator<GoldManagerBean> CREATOR = new Parcelable.Creator<GoldManagerBean>() {
        @Override
        public GoldManagerBean createFromParcel(Parcel source) {
            return new GoldManagerBean(source);
        }

        @Override
        public GoldManagerBean[] newArray(int size) {
            return new GoldManagerBean[size];
        }
    };
}
