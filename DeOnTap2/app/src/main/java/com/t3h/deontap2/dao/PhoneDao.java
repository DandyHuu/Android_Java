package com.t3h.deontap2.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.t3h.deontap2.model.Employee;

import java.util.List;

@Dao
public interface PhoneDao  {
    @Query("SELECT * FROM Employee")
    List<Employee> getAll();
}
