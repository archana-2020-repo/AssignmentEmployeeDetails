package com.example.androidasignment.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidasignment.database.DBConfig;
import com.example.androidasignment.model.Employee;
import com.example.androidasignment.network.Api;
import com.example.androidasignment.network.ApiClient;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private Context mContext;
    public MutableLiveData<List<Employee>> data = new MutableLiveData<>();


    public void getData(Context activity) {
        /** Here we need to add viewmodel factory calss to get context in viewmodel*/
        this.mContext = activity;


        Api apiService = ApiClient.getClient().create(Api.class);
        Observable<List<Employee>> checkConnection = apiService.getEmployeeList();
        checkConnection.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Employee>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Employee> employeeList) {
                        insertIntoDB(employeeList, activity);
                        Log.d("Responseget", " ------updateOpeningBalance" + employeeList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Responseget", e + " ------error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void insertIntoDB(List<Employee> employeeList, Context activity) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                if (employeeList != null)
                    DBConfig.getDatabase(activity).testDao().insert(employeeList);

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d("Responseget",  " ------db updated");

                getDataFromDb(mContext);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void getDataFromDb(Context activity) {
        this.mContext = activity;

        getAllEmployees()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Employee>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Employee> employeeList) {
                        if(employeeList != null) {
                            Log.d("Responseget", " ------db data  " + employeeList.size());
                            if (employeeList.size() != 0) {
                                data.setValue(employeeList);
                            } else {
                                getData(mContext);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Observable<List<Employee>> getAllEmployees() {
        return Observable.create(subscriber -> {
            subscriber.onNext(DBConfig.getDatabase(mContext).testDao().getAllEmployees());
        });
    }
}