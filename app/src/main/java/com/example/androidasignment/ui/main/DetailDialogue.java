package com.example.androidasignment.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;

import com.example.androidasignment.R;
import com.example.androidasignment.model.Employee;

import java.util.List;

public class DetailDialogue extends Dialog {
    private Context context;
    private List<Employee> data;

    public DetailDialogue(@NonNull Context context, List<Employee> data) {
        super(context);
        this.context = context;
        this.data = data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_detail_item);
    }

    public void setUi() {
        // set UI
    }

}
