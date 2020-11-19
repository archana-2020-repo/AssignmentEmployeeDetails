package com.example.androidasignment.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

public class CompanyConverter implements Serializable {
    //
    @TypeConverter // note this annotation
    public String fromOptionValuesList(Company optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Company>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public Company toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Company>() {
        }.getType();
        Company ticketAddItemModels = gson.fromJson(optionValuesString, type);
        return ticketAddItemModels;
    }
}
