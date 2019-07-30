package com.t3h.baitapbuoi11.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.t3h.baitapbuoi11.model.Stories;

import java.util.List;

@Dao
public interface StoriesDao {
    @Query("SELECT * FROM Stories")
    List<Stories> getStudent();

    @Insert
    void insert(Stories student);

    @Update
    void update(Stories student);

    @Delete
    void delete(Stories student);
}
