package com.gmonetix.myapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Gaurav Bordoloi on 1/19/2018.
 */

@Entity
public class Page {

    @PrimaryKey
    @NonNull
    private String id;

    private String title;

    public Page(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
