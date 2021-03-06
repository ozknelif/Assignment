package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button getSensorButton = findViewById(R.id.getSensorButton);
        final Button viewUsersButton = findViewById(R.id.viewUserListBttn);
        final Button sharedPreferenceButton = findViewById(R.id.shared_button);
        final Button emailButton = findViewById(R.id.emailSendBttn);
        final Button takeNotesButton = findViewById(R.id.take_notes_button);
        final Button threadButton = findViewById(R.id.threadButton);
        final Button setAlarmButton = findViewById(R.id.alarmButton);
        final Button locationButton = findViewById(R.id.locationButton);
        final Button detectButton = findViewById(R.id.detectButton);

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detIntent = new Intent(Menu.this, DetectMovement.class);
                startActivity(detIntent);
            }
        });
        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent locationIntent = new Intent(Menu.this, GetLocation.class);
                startActivity(locationIntent);
            }
        });
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent alarmIntent = new Intent(Menu.this, AlarmActivity.class);
                startActivity(alarmIntent);
            }
        });
        threadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent threadIntent = new Intent(Menu.this, ThreadActivity.class);
                startActivity(threadIntent);
            }
        });
        getSensorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent lightSensorIntent = new Intent(Menu.this, LightSensor.class);
                startActivity(lightSensorIntent);
            }
        });
        viewUsersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent recyclerViewIntent = new Intent(Menu.this, ViewUsers.class);
                startActivity(recyclerViewIntent);
            }
        });
        sharedPreferenceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int value = getIntent().getIntExtra("key",0);
                Intent sharedPreferenceIntent = new Intent(Menu.this, SaveData.class);
                sharedPreferenceIntent.putExtra("son",value);
                startActivity(sharedPreferenceIntent);
            }
        });
        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent emailComposingIntent = new Intent(Menu.this, EmailComposing.class);
                startActivity(emailComposingIntent);
            }
        });
        takeNotesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent notesIntent = new Intent(Menu.this, ViewNotes.class);
                startActivity(notesIntent);
            }
        });
    }
}
