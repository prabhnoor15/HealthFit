package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.fragment;
import static com.prabh.cheeku.healthfit.R.id.textView;

public class calorie_2 extends AppCompatActivity {
    public static String fin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MainActivity.choice > 24.9 ||MainActivity.choice < 18.5 ) {
            setTheme(R.style.you);
        }
        if(MainActivity.choice <= 24.9 && MainActivity.choice >= 18.5) {
            setTheme(R.style.you2);
        }

        setContentView(R.layout.activity_calorie_2);


        //EditText nuj = (EditText) findViewById(R.id.foodw);
        //fin = nuj.getText().toString().trim();
    }

    public void vip(View v) {
        EditText nuj = (EditText) findViewById(R.id.foodw);
        fin = nuj.getText().toString().trim();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(this, "No Internet",
                    Toast.LENGTH_SHORT).show();
        }

        else if(calorie_2.fin.equals("")){
            Toast.makeText(this, "No Input",
                    Toast.LENGTH_SHORT).show();
        }
        else{
        Intent intent = new Intent(this, five.class);
        startActivity(intent);
    }}
}
