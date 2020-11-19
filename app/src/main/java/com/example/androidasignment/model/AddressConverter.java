package com.example.androidasignment.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

public class AddressConverter implements Serializable {
    @TypeConverter // note this annotation
    public String fromOptionValuesList(Address optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Address>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public Address toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Address>() {
        }.getType();
        Address ticketAddItemModels = gson.fromJson(optionValuesString, type);
        return ticketAddItemModels;
    }
}
