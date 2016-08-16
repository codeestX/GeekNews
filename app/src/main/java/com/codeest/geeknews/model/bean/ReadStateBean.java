package com.codeest.geeknews.model.bean;

import io.realm.RealmObject;

/**
 * Created by codeest on 16/8/17.
 */

public class ReadStateBean extends RealmObject{

    private int id;

    public ReadStateBean() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
