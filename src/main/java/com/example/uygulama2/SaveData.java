package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SaveData extends AppCompatActivity {
    private EditText editAge,editWeight,editHeight;
    private Button saveButton;
    private TextView editPassword,editUsername;
    private Spinner gender_spinner;
    private Spinner mode_spinner;
    private int index;
    public static final String myPreferences = "MyPrefs" ;
    public static final String password = "passwordKey";
    public static final String age = "ageKey";
    public static final String weight = "weightKey";
    public static final String height = "heightKey",mode = "modeKey",gender="genderKey";
    private ArrayAdapter<CharSequence> gAdapter,mAdapter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        gender_spinner = findViewById(R.id.spinner_gender);
        gAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        gAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(gAdapter);
        mode_spinner = findViewById(R.id.spinner_app_mode);
        mAdapter = ArrayAdapter.createFromResource(this, R.array.app_mode_array, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mode_spinner.setAdapter(mAdapter);
        editPassword = findViewById(R.id.shared_password_edit);
        editAge = findViewById(R.id.shared_age_edit);
        editWeight = findViewById(R.id.shared_weight_edit);
        editHeight = findViewById(R.id.shared_height_edit);
        editUsername = findViewById(R.id.shared_username_text);
        saveButton = findViewById(R.id.shared_save_button);
        index = getIntent().getIntExtra("son",0);
        editUsername.setText(MainActivity.users.get(index).getUname());
        editPassword.setText(MainActivity.users.get(index).getPassword());
        firstLoad();
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateData();
            }
        });
    }


    private void firstLoad()  {
        sharedPreferences = getSharedPreferences(myPreferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.contains(age)){
            editAge.setText(sharedPreferences.getString(age,""));
        }else{
            editAge.setText(MainActivity.users.get(index).getAge());
            editor.putString(age, editAge.getText().toString());
            editor.apply();
        }
        if(sharedPreferences.contains(weight)){
            editWeight.setText(sharedPreferences.getString(weight,""));
        }else{
            editWeight.setText(MainActivity.users.get(index).getWeight());
            editor.putString(weight, editWeight.getText().toString());
            editor.apply();
        }
        if(sharedPreferences.contains(height)){
            editHeight.setText(sharedPreferences.getString(height,""));
        }else{
            editHeight.setText(MainActivity.users.get(index).getHeight());
            editor.putString(height, editHeight.getText().toString());
            editor.apply();
        }
        if(sharedPreferences.contains(mode)){
            mode_spinner.setSelection(mAdapter.getPosition(sharedPreferences.getString(mode,"")));
        }else{
            if(MainActivity.users.get(index).getApp_mode().equals("dark")){
                mode_spinner.setSelection(mAdapter.getPosition("Dark Mode"));
                editor.putString(mode, "Dark Mode");
            }else{
                mode_spinner.setSelection(mAdapter.getPosition("Light Mode"));
                editor.putString(mode, "Light Mode");
            }
            editor.apply();
        }
        if(sharedPreferences.contains(gender)){
            gender_spinner.setSelection(gAdapter.getPosition(sharedPreferences.getString(gender,"")));
        }else{
            if(MainActivity.users.get(index).getGender().equals("F")){
                gender_spinner.setSelection(gAdapter.getPosition("Female"));
                editor.putString(gender, "Female");
            }else{
                gender_spinner.setSelection(gAdapter.getPosition("Male"));
                editor.putString(gender, "Male");
            }
            editor.apply();
        }
    }

    private void updateData() {
        String userage = editAge.getText().toString();
        String userweight = editWeight.getText().toString();
        String userheight = editHeight.getText().toString();


        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(age, userage);
        editor.putString(weight, userweight);
        editor.putString(height, userheight);
        editor.putString(gender, String.valueOf(gender_spinner.getSelectedItem()));
        editor.putString(mode, String.valueOf(mode_spinner.getSelectedItem()));
        editor.apply();
        editor.commit();
        Toast.makeText(SaveData.this,"Thanks",Toast.LENGTH_LONG).show();
        finish();
    }


}
