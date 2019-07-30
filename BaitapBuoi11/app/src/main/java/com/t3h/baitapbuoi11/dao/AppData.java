package com.t3h.baitapbuoi11.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.t3h.baitapbuoi11.model.Stories;

@Database(entities = {Stories.class},version = 1)
public abstract class AppData extends RoomDatabase {
    public static AppData instance;

    public static AppData getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder( context,
                    AppData.class,
                    "stories-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StoriesDao getStoryDao();
}
