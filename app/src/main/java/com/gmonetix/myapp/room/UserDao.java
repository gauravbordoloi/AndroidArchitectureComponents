package com.gmonetix.myapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.gmonetix.myapp.model.Page;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Gaurav Bordoloi on 1/19/2018.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM page")
    List<Page> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Page page);

}
