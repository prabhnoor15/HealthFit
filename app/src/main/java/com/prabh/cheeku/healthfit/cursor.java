package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.prabh.cheeku.healthfit.data.contract;

import static android.content.ContentValues.TAG;

/**
 * Created by cheeku on 2017-08-30.
 */

public class cursor extends CursorAdapter {
    public static double bmi = 0;
     public Double y;
    public cursor(Context context, Cursor c) {

        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.num);
        TextView summaryTextView = (TextView) view.findViewById(R.id.primary);
        TextView date = (TextView) view.findViewById(R.id.secondry);

        int nameColumnIndex = cursor.getColumnIndex(contract.cont.COLUMN_WEIGHT);
        int breedColumnIndex = cursor.getColumnIndex(contract.cont.COLUMN_HEIGHT);
        int inchfactor = cursor.getColumnIndex(contract.cont.COLUMN_AGE);
        int tm = cursor.getColumnIndex(contract.cont.COLUMN_NAME);
        int tm2 = cursor.getColumnIndex(contract.cont.COLUMN_RACE);




        String petName = cursor.getString(nameColumnIndex);
        String petBreed = cursor.getString(breedColumnIndex);
        String inch = cursor.getString(inchfactor);
        String all = cursor.getString(tm);
        String kad = cursor.getString(tm2);
       int k =Integer.parseInt(petName);// weight
        int z = Integer.parseInt(petBreed);// height foot
        double x = Double.parseDouble(inch); // BMI
        int z2 = Integer.parseInt(kad);// height inch


        if(x == 10){
            kad =  Integer.toString(z) + ".10" ;
            Log.d(TAG, "outcome = " + kad);
        }
        else if(x == 11){
            kad =  Integer.toString(z) + ".11" ;
            Log.d(TAG, "outcome = " + kad);
        }
        else{

            kad =  Integer.toString(z) + "." + Integer.toString(z2);
            Log.d(TAG, "outcome = " + kad);
        }



        String total2 = String.format("%.2f", x);
        // Update the TextViews with the attributes for the current entry
        nameTextView.setText(total2);
        summaryTextView.setText("WEIGHT- " + petName + "  HEIGHT- " + kad);
        date.setText(all);

    }

    public static double getHealth(){
    return bmi;
    }






}
