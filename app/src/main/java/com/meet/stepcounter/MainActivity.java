package com.meet.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    TextView t1;
    boolean run=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.steps);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);



    }

    @Override
    protected void onResume() {
        super.onResume();

    run=true;
    Sensor countsensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

    if(countsensor!=null){

        sensorManager.registerListener(this,countsensor,sensorManager.SENSOR_DELAY_UI);

    }else {
        Toast.makeText(this, "sensor not found!!", Toast.LENGTH_SHORT).show();
    }


    }

    @Override
    protected void onPause() {
        super.onPause();
    run=false;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(run){
            t1.setText(String.valueOf(sensorEvent.values[0]));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
