package com.gmonetix.myapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.gmonetix.myapp.model.Page;

/**
 * Created by Gaurav Bordoloi on 1/19/2018.
 */

@Database(entities = {Page.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase{

    public abstract UserDao userDao();

}
