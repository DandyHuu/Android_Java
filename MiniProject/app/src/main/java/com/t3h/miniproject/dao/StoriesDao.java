package com.t3h.miniproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.t3h.miniproject.model.tbNews;

import java.util.List;


@Dao
public interface StoriesDao {
    @Query("SELECT * FROM tbnews ORDER BY id DESC")
    List<tbNews> getStudent();

    @Query("SELECT * FROM tbnews WHERE hobbit == :hobbies")
    List<tbNews> getStudentHobbit(boolean hobbies);

    @Query("SELECT * FROM tbnews WHERE title == :title_ ")
    List<tbNews> getStudentIsset(String title_);

    @Query("SELECT * FROM tbnews WHERE title == :title_ AND hobbit == :hobbies")
    List<tbNews> getStudentHobbitIsset(String title_,boolean hobbies);

    @Query("UPDATE tbnews SET hobbit = :hobbies WHERE title == :title_ ")
    void removeStudentHobbit(String title_,boolean hobbies);
    @Query("UPDATE tbnews SET hobbit = :hobbies WHERE title == :title_ ")
    void upDateStudentHobbit(String title_,boolean hobbies);

    @Query("DELETE FROM tbnews WHERE title == :title_")
    void removeStudentSaved(String title_);

    @Insert
    void insert(tbNews news);

    @Update
    void update(tbNews news);

    @Delete
    void delete(tbNews news);
}
