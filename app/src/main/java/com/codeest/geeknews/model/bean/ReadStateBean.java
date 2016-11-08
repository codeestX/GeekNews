package com.codeest.geeknews.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by codeest on 16/8/17.
 */

public class ReadStateBean extends RealmObject{

    @PrimaryKey
    private int id;

    public ReadStateBean() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
