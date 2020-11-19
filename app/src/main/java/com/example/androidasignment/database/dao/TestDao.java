package com.example.androidasignment.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidasignment.database.DBConfig;
import com.example.androidasignment.model.Employee;

import java.util.List;

@Dao
public interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Employee> employeeList);

    @Query("SELECT * FROM " + DBConfig.table_employee)
    List<Employee> getAllEmployees();
}
