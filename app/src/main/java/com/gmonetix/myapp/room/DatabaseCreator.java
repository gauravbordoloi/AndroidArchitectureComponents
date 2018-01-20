package com.gmonetix.myapp.room;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Gaurav Bordoloi on 1/19/2018.
 */

//singleton class
public class DatabaseCreator {

    private static AppDatabase appDatabase;
    private static final Object LOCK = new Object();

    public synchronized static AppDatabase getAppDatabase(Context context){
        if(appDatabase == null) {
            synchronized (LOCK) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context,
                            AppDatabase.class, "page").allowMainThreadQueries().build();
                }
            }
        }
        return appDatabase;
    }
}