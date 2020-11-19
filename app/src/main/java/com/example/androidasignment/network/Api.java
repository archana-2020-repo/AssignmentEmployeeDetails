package com.example.androidasignment.network;

import com.example.androidasignment.model.Employee;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("5d565297300000680030a986")
    Observable<List<Employee>> getEmployeeList();
}
