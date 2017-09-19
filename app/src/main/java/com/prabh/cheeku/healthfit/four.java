package com.prabh.cheeku.healthfit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class four extends AppCompatActivity {
   // public static String check;
 //  public static String check2 = "aru";
   // public  EditText food2;
   private Uri mCurrent;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mCurrent = intent.getData();
       if(MainActivity.choice <= 24.9 &&MainActivity.choice >= 18.5){
           setTheme(R.style.you2);
       }
        if(MainActivity.choice > 24.9 || MainActivity.choice < 18.5) {
            setTheme(R.style.you);
        }
        setContentView(R.layout.frag);
        if(MainActivity.choice > 24.9 || MainActivity.choice < 18.5) {
            ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewpager);
            viewPager2.setBackgroundColor(Color.WHITE);
            LinearLayout l = findViewById(R.id.gg);
            l.setBackgroundResource(R.color.colorAcc);
        }
        if(MainActivity.choice <= 24.9 &&MainActivity.choice >= 18.5) {
            ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewpager);
            viewPager2.setBackgroundColor(Color.WHITE);
            LinearLayout l = findViewById(R.id.gg);
            l.setBackgroundResource(R.color.col);
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);


        category adapter = new category(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        tabLayout.setupWithViewPager(viewPager);
       // EditText food2 = (EditText) viewPager.findViewById(R.id.food);
        //check = food2.getText().toString().trim();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.single:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getContentResolver().delete(mCurrent, null, null);
                Intent intent = new Intent(four.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    }
