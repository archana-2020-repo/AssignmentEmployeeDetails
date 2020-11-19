package com.example.androidasignment.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.androidasignment.database.dao.TestDao;
import com.example.androidasignment.model.Employee;
import com.example.androidasignment.model.Test;

@Database(entities = {
        Test.class,
        Employee.class
}, version = 1, exportSchema = false)
public abstract class DBConfig extends RoomDatabase {

    public static final String table_employee = "employee";

    public abstract TestDao testDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {
        INSTANCE.clearAllTables();
    }

    private static DBConfig INSTANCE = null;
    public static String DB_NAME = "test_db";

    public static DBConfig getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, DBConfig.class,  DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
