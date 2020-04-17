package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.*;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private int count =0 ;
    Person p1 = new Person("ali", "111", "M", "dark", "65", "170", "24");
    Person p2 = new Person("veli", "222", "M", "dark", "74", "170", "26");
    Person p3 = new Person("ayşe", "333", "F", "light", "80", "170", "29");
    Person p4 = new Person("fatma", "444", "F", "dark", "57", "170", "21");
    static ArrayList<Person> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users.add(p1);
        users.add(p2);
        users.add(p3);
        users.add(p4);
        final EditText username = findViewById(R.id.user_name_text);
        final EditText password = findViewById(R.id.password_text);
        final Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkPassword(username.getText().toString(), password.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                    Intent menuIntent = new Intent(MainActivity.this, Menu.class);
                    menuIntent.putExtra("key", getIndex(username.getText().toString(), password.getText().toString()));
                    startActivity(menuIntent);
                } else {
                    username.setText("");
                    password.setText("");
                    count++;
                    if(count == 3){
                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

            }
        });

    }
    private int getIndex(String uname, String pw){
        for(Person p : users){
            if(p.getUname().equals(uname) && p.getPassword().equals(pw)){
                return users.indexOf(p);
            }
        }
        return 0;
    }
    private boolean checkPassword(String username, String password) {
        for (Person p : users) {
            if (p.getUname().equals(username) && p.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}