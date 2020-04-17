package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static java.lang.Math.abs;

public class LightSensor extends AppCompatActivity implements SensorEventListener {
    private TextView sensorList;
    private SensorManager sMgr;
    private Sensor light;
    private Sensor accel;
    long curTime, lastUpdate;
    float x, x_old, y, y_old, z, z_old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);
        sensorList = findViewById(R.id.sensorListText);
        sMgr =  (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sMgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        accel = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        List<Sensor> mList= sMgr.getSensorList(Sensor.TYPE_ALL);
        StringBuffer allSensors = new StringBuffer();
        for (Sensor sens : mList)
        {
            allSensors.append(sens.getName() + "\n");
        }
        sensorList.setText(allSensors.toString());
    }
    protected void onPause() {
        super.onPause();
        sMgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sMgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sMgr.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_LIGHT) {
            float lux = event.values[0];
            if (lux < 40) {
                sensorList.setBackgroundColor(Color.BLACK);
                sensorList.setTextColor(Color.WHITE);
            } else {
                sensorList.setBackgroundColor(Color.WHITE);
                sensorList.setTextColor(Color.BLACK);
            }
        }
        else if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            if (abs((x_old*x_old+y_old*y_old+z_old*z_old) -  (x*x+y*y+z*z)) > 10 ) {
                curTime = System.currentTimeMillis();
                x_old = x;
                y_old = y;
                z_old = z;
            } else {
                lastUpdate = System.currentTimeMillis() - curTime;
                if (lastUpdate > 5000) {
                    Toast.makeText(getApplicationContext(),"App is closing..",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
