package com.example.uygulama2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<Person> users;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, password;
        MyViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.rvUsername);
            password = view.findViewById(R.id.rvPassword);
        }
    }
    public UserAdapter(List<Person> users) {
        this.users = users;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person user = users.get(position);
        holder.username.setText(user.getUname());
        holder.password.setText(user.getPassword());
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
}

