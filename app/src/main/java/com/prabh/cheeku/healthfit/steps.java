package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class steps extends Fragment implements SensorEventListener , StepListener {
    private TextView textView;
    private stepdetector simpleStepDetector;
    private SensorManager sensorManager;
     private TextView TvSteps;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    Button BtnStart;
    private TextView TvStep;

    public steps() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_step, container, false);
          sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
          accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
         simpleStepDetector = new stepdetector();
        simpleStepDetector.registerListener(steps.this);

         TvSteps = (TextView) V.findViewById(R.id.tv_steps);
        TvStep = (TextView) V.findViewById(R.id.tv);
        FloatingActionButton Page = (FloatingActionButton) V.findViewById(R.id.hell);
        FloatingActionButton stop = (FloatingActionButton) V.findViewById(R.id.heaven);

        Page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSteps = 0;
                Toast.makeText(getActivity(),"STARTED",
                        Toast.LENGTH_SHORT).show();
                TvSteps.setText("0 steps");
                TvStep.setText("Pedometer Running");
                sensorManager.registerListener(steps.this, accel, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager.unregisterListener(steps.this);
                TvStep.setText("Pedometer Stoped");
                Toast.makeText(getActivity(),"DONE",
                        Toast.LENGTH_SHORT).show();
            }
        });



        return V;
    }





    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor event, int i) {
 }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvStep.setText("Pedometer Running");
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }
}
