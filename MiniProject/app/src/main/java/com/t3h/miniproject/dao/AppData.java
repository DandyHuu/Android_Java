package com.t3h.miniproject.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.t3h.miniproject.model.tbNews;

@Database(entities = {tbNews.class},version = 1)
public abstract class AppData extends RoomDatabase {
    public static AppData instance;

    public static AppData getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder( context,
                    AppData.class,
                    "tbnews-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StoriesDao getStoryDao();
}
