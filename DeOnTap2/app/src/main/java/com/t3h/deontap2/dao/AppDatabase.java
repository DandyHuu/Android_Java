package com.t3h.deontap2.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.t3h.deontap2.model.Employee;

@Database(entities = {Employee.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase intance;

    public static AppDatabase getIntance(Context context) {
        if (intance == null) {
            intance = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "EmployeeDB.sqlite"
                    ).createFromAsset("EmployeeDB.sqlite")
                    .allowMainThreadQueries()
                    .build();
        }
        return intance;
    }
    public abstract PhoneDao getEmployeeDao();

}
