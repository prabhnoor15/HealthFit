package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.zip.Inflater;


public class calorie extends Fragment {

   public static String check2 ;
    public  EditText food2;




    public calorie() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calorie, container, false);
     //  food2 = (EditText) v.findViewById(R.id.food);

    // food2.setOnClickListener(new View.OnClickListener() {
      //  public void onClick(View v) {
        //     check2 = food2.getText().toString().trim();
         //}});


        FloatingActionButton newPage = (FloatingActionButton)v.findViewById(R.id.fab);
        if(MainActivity.choice > 24.9 || MainActivity.choice < 18.5) {
            newPage.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorA)));
        }
      //  if(MainActivity.choice <= 24.9 && MainActivity.choice >18.5) {
      //      newPage.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.col)));
      //  }
        if(MainActivity.choice >= 18.5 && MainActivity.choice <= 24.9) {
            newPage.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.col)));
        }
        newPage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), calorie_2.class);
                startActivity(intent);
            }
        });

        return v;

}







}
