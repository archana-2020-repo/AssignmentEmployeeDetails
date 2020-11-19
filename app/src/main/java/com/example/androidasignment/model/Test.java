package com.example.androidasignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test")
public class Test {
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
