package com.example.androidasignment.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidasignment.R;
import com.example.androidasignment.model.Employee;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.DataViewHolder> {
    private Context context;
    private List<Employee> data;

    public ListAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.data = employees;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Employee dataModel = data.get(position);
         holder.empName.setText(dataModel.getName());
         //holder.empCompany.setText(dataModel.getCompany().getName());
        Glide.with(context).load(dataModel.getProfileImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .circleCrop()
                .into(holder.empImage);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView empImage;
        TextView empName;
        TextView empCompany;
        DataViewHolder(View itemView) {
            super(itemView);
            empImage = itemView.findViewById(R.id.emp_img);
            empName = itemView.findViewById(R.id.emp_name);
            empCompany = itemView.findViewById(R.id.emp_company);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            DetailDialogue dialogue = new DetailDialogue(context, data);
            dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogue.setCancelable(true);
            dialogue.show();
        }
    }
}
