package com.prabh.cheeku.healthfit;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class sec extends AppCompatActivity {
    public Spinner gender;
    public Spinner ethinicity;
    public int gender_val = 0;
    public int ethn_val = 0;
    private String[] arraySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_sec);
        gender = (Spinner) findViewById(R.id.spinner_gender);
        ethinicity = (Spinner) findViewById(R.id.spinner);
        this.arraySpinner = new String[] {
                "unknown", "Male", "Female"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Male")) {
                        gender_val = 1;
                    } else if (selection.equals("Female")) {
                        gender_val = 2;
                    } else {
                        gender_val = 0;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender_val = 0;
                Toast.makeText(sec.this, "NothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

        this.arraySpinner = new String[] {
                "Unknown" ,"Asian", "African-American", "White", "Hispanic"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        ethinicity.setAdapter(adapter2);
        ethinicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Asian")) {
                        ethn_val = 1;
                    } else if (selection.equals("African-American")) {
                        ethn_val = 2;
                    }

                   else if (selection.equals("White")) {
                        ethn_val = 3;
                    }
                    else if (selection.equals("Unknown")) {
                        ethn_val = 0;
                    }
                    else {
                        ethn_val = 4;
                    }
                }
                }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            ethn_val = 0;
                Toast.makeText(sec.this, "NothingSelected", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void  Order2(View v){
        Intent intent = new Intent(sec.this, third.class);
        startActivity(intent);
    }




}
