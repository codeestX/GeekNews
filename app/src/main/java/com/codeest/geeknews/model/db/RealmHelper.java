package com.codeest.geeknews.model.db;

import android.content.Context;

import com.codeest.geeknews.model.bean.ReadStateBean;
import com.codeest.geeknews.model.bean.RealmLikeBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by codeest on 16/8/16.
 */

public class RealmHelper {

    private Realm mRealm;

    public RealmHelper(Context mContext) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(mContext)
                .deleteRealmIfMigrationNeeded()
                .name("myRealm.realm")
                .build());
    }

    public void insertNewsId(int id) {
        mRealm.beginTransaction();
        ReadStateBean bean = mRealm.createObject(ReadStateBean.class);
        bean.setId(id);
        mRealm.commitTransaction();
    }

    public boolean queryNewsId(int id) {
        RealmResults<ReadStateBean> results = mRealm.where(ReadStateBean.class).findAll();
        for(ReadStateBean item : results) {
            if(item.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void insertLikeBean(RealmLikeBean bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(bean);
        mRealm.commitTransaction();
    }

    public void deleteLikeBean(String id) {
        RealmLikeBean data = mRealm.where(RealmLikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        data.deleteFromRealm();
        mRealm.commitTransaction();
    }

    public boolean queryLikeId(String id) {
        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAll();
        for(RealmLikeBean item : results) {
            if(item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<RealmLikeBean> getLikeList() {
        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAll();
        return mRealm.copyFromRealm(results);
    }
}
