package com.jastipapp.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter<T extends Inflatable> extends RecyclerView.Adapter<TodoHolder> {

    private ArrayList<T> tasks;

    private Context mContext;

    public TodoAdapter(Context context) {
        tasks = new ArrayList<T>();
        this.mContext = context;
    }

    public void addTask(T task) {
        tasks.add(task);
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_list_item,  viewGroup, false);

        TodoHolder v = new TodoHolder(itemView);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder todoHolder, int i) {
        TodoHolder holder = todoHolder;
        holder.mTitle.setText(tasks.get(i).getItemText1());
        holder.mCount.setText(tasks.get(i).getItemText3());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}