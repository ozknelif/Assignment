package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
public class ViewUsers extends AppCompatActivity {
    private UserAdapter uAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        uAdapter = new UserAdapter(MainActivity.users);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uAdapter);
        fillData();
        Toast.makeText(getApplicationContext(),MainActivity.users.get(0).getPassword(),Toast.LENGTH_LONG);

    }

    private void fillData() {
        uAdapter.notifyDataSetChanged();
    }
}
