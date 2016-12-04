package com.codeest.geeknews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by codeest on 16/12/1.
 */

public class GoldManagerItemBean extends RealmObject implements Parcelable {

    private int index;

    private boolean isSelect;

    public GoldManagerItemBean () {

    }

    public GoldManagerItemBean (int index, boolean isSelect) {
        this.index = index;
        this.isSelect = isSelect;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.index);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    protected GoldManagerItemBean(Parcel in) {
        this.index = in.readInt();
        this.isSelect = in.readByte() != 0;
    }

    public static final Parcelable.Creator<GoldManagerItemBean> CREATOR = new Parcelable.Creator<GoldManagerItemBean>() {
        @Override
        public GoldManagerItemBean createFromParcel(Parcel source) {
            return new GoldManagerItemBean(source);
        }

        @Override
        public GoldManagerItemBean[] newArray(int size) {
            return new GoldManagerItemBean[size];
        }
    };
}
