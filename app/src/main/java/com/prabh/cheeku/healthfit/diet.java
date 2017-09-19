package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class diet extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(MainActivity.choice > 24.99){
        return inflater.inflate(R.layout.fragment_diet, container, false);
    }
        else if(MainActivity.choice <= 24.9 && MainActivity.choice >18.5) {
            return inflater.inflate(R.layout.fragment_diet2, container, false);
        }
    else{
            return inflater.inflate(R.layout.diet3, container, false);
        }

}}
