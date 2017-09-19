package com.prabh.cheeku.healthfit;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.prabh.cheeku.healthfit.data.contract;

public class third extends AppCompatActivity {
    private EditText weight;

    public static  int k;
    private EditText height;

    private EditText age;

    public String nameString;
    public String breedString;
    public String weightString;

    private boolean bmi_change = false;

     double weg = 0;
     double heg = 0;
    double aag = 0;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            bmi_change = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.heightt);
        age = (EditText) findViewById(R.id.age);

        weight.setOnTouchListener(mTouchListener);
        height.setOnTouchListener(mTouchListener);
        age.setOnTouchListener(mTouchListener);

    }




    private void insert() {
         nameString = weight.getText().toString().trim();
         breedString = height.getText().toString().trim();
         weightString = age.getText().toString().trim();
        if (!TextUtils.isEmpty(nameString) && !TextUtils.isEmpty(breedString) &&  !TextUtils.isEmpty(weightString)){
            weg = Double.parseDouble(weight.getText().toString().trim());
            heg = Double.parseDouble(height.getText().toString().trim());
            aag = Double.parseDouble(age.getText().toString().trim());
            double xxx;
            xxx =   (heg * 12) ;
            xxx += aag;
            xxx = xxx * 0.0254;
            xxx = xxx * xxx;
            xxx = weg / xxx;
              if(aag > 11){
                Toast.makeText(this, "INVALID HEIGHT",
                        Toast.LENGTH_SHORT).show();
            }
            else{
            ContentValues values = new ContentValues();

            String dd;

            long da = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
            dd = sdf.format(da);

            values.put(contract.cont.COLUMN_NAME, dd);
            values.put(contract.cont.COLUMN_AGE, xxx);
            values.put(contract.cont.COLUMN_GENDER, 2);
            values.put(contract.cont.COLUMN_HEIGHT, heg);
            values.put(contract.cont.COLUMN_RACE, aag);
            values.put(contract.cont.COLUMN_WEIGHT, weg);
            Uri newUri = getContentResolver().insert(contract.cont.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "failed",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                String bmi = String.format("%.2f", xxx);
                Toast.makeText(this, "Your Bmi is " + bmi,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(third.this, MainActivity.class);
                startActivity(intent);
            }
        }}
        else {
            Toast.makeText(this, "Fill All Fields",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }





    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                    insert();

                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
