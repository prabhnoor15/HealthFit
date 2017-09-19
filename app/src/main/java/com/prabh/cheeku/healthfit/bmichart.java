package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class bmichart extends Fragment {
    public EditText foot;
    public EditText inch;
    public TextView set;
    public double x;
    public int sex;
    public double x2;
    public Spinner gender2;
    private String[] arraySpinner2;

    public bmichart() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V =  inflater.inflate(R.layout.fragment_bmichart, container, false);
        foot = V.findViewById(R.id.what);
        inch = V.findViewById(R.id.w);
        set = V.findViewById(R.id.textView2);
        gender2 = (Spinner) V.findViewById(R.id.spinnergender);
        this.arraySpinner2 = new String[] {
                 "Male", "Female"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, arraySpinner2);
        gender2.setAdapter(adapter);
        gender2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Male")) {
                        sex = 1;
                    } else {
                        sex = 2;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        FloatingActionButton myFab = (FloatingActionButton) V.findViewById(R.id.YOYO);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!foot.getText().toString().trim().isEmpty() && !inch.getText().toString().trim().isEmpty()
                        ){
                        x = Double.parseDouble(foot.getText().toString().trim());
                        x2 = Double.parseDouble(inch.getText().toString().trim());
                        if (x2 < 10) {
                            x2 /= 10;
                            x = x + x2;
                        }
                        if (x2 > 10) {
                            x = x + (x2 / 100);
                        }
                        if(x2 == 10){
                            x = x + 0.10;
                        }

                    if(sex == 1) {
                         if(x == 5.1 && x2 == 10) {
                             set.setText( "67.6-83 kg");
                         }
                        else if(x == 4.1 && x2 == 10) {
                            set.setText( "38.5-46.7 kg");
                        }
                         else if(x == 6.1 && x2 == 10){
                             set.setText( "97-118.8 kg");
                         }
                       else{
                        int i = 0;
                        int k = 0;
                        double[] myArray ;
                        myArray = new double[] {4.6 , 4.7 , 4.8 , 4.9 , 4.12, 4.11 , 5.0 ,5.1 , 5.2 ,5.3 ,5.4 , 5.5,
                        5.6 , 5.7, 5.8 , 5.9 , 5.10 , 5.11 , 6.0 , 6.1 , 6.2 , 6.3 , 6.4 , 6.5 , 6.6 , 6.7 , 6.8 , 6.9,
                                6.10 , 6.11 , 7.0
                        };
                        String[] myArray2 ;
                        myArray2 = new String[] {"28.5-34.9 kg","30.8-38.1 kg" ,"33.5-40.8 kg" , "35.8-43.9 kg" ,"38.5-46.7 kg",
                                "40.8-49.9 kg" ,"43.1-53 kg","45.8-55.8 kg", "48.1-58.9 kg" , "50.8-61.6 kg" , "53-64.8 kg" ,
                                "55.3-68 kg" ,"58-70.7 kg" ,"60.3-73.9 kg" ,"63-76.6 kg" ,"65.3-79.8 kg" ,"67.6-83 kg",
                                "70.3-85.7 kg" ,"72.6-88.9 kg" ,"75.3-91.6 kg" ,"77.5-94.8 kg" ,"79.8-98 kg","82.5-100.6 kg",
                                "84.8-103.8 kg" ,"87.5-106.5 kg","89.8-109.7 kg","92-112.9 kg","94.8-115.6 kg","97-118.8 kg",
                                "99.8-121.5 kg" ,"102-124.7 kg"};
                        for( i = 0 ; i < 30 ; i++){
                            if(x == myArray[i]) {
                                break;
                            }
                            else {
                                k++;
                            }
                        }
                        if(i == 30 && !(x == 7.0)){
                            Toast.makeText(getActivity(),"INVALID INPUT",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                        String nono =  myArray2[i] ;
                        set.setText( nono);
                        }}}
                    if(sex == 2) {
                        if(x == 5.1 && x2 == 10) {
                            set.setText( "61.2-74.8 kg");
                        }
                        else if(x == 4.1 && x2 == 10) {
                            set.setText( "36.4-44.9 kg");
                        }
                        else if(x == 6.1 && x2 == 10){
                            set.setText( "85.7-104.8 kg");
                        }
                        else{
                        int i = 0;
                        int k = 0;
                        double[] myArray ;
                        myArray = new double[] {4.6 , 4.7 , 4.8 , 4.9 , 4.12, 4.11 , 5.0 ,5.1 , 5.2 ,5.3 ,5.4 , 5.5,
                                5.6 , 5.7, 5.8 , 5.9 , 5.10 , 5.11 , 6.0 , 6.1 , 6.2 , 6.3 , 6.4 , 6.5 , 6.6 , 6.7 , 6.8 , 6.9,
                                6.10 , 6.11 , 7.0
                        };
                        String[] myArray2 ;
                        myArray2 = new String[] {"28.5-34.9 kg","30.8-37.6 kg" ,"32.6-39.9 kg" , "34.9-42.6 kg" ,"36.4-44.9 kg",
                                "39-47.6 kg" ,"40.8-49.9 kg","43.1-52.6 kg", "44.9-54.9 kg" , "47.2-57.6 kg" , "49-59.9 kg" ,
                                "51.2-62.6 kg" ,"53-64.8 kg" ,"55.3-67.6 kg" ,"57.1-69.8 kg" ,"59.4-72.6 kg" ,"61.2-74.8 kg",
                                "63.5-77.5 kg" ,"65.3-79.8 kg" ,"67.6-82.5 kg" ,"69.4-84.8 kg" ,"71.6-87.5 kg","73.5-89.8 kg",
                                "75.7-92.5 kg" ,"77.5-94.8 kg","79.8-97.5 kg","81.6-99.8 kg","83.9-102.5 kg","85.7-104.8 kg",
                                "88-107.5 kg" ,"89.8-109.7 kg"};
                        for( i = 0 ; i < 30 ; i++){
                            if(x == myArray[i]) {
                                break;
                            }
                            else {
                                k++;
                            }
                        }
                        if(i == 30 && !(x == 7.0)){
                            Toast.makeText(getActivity(),"INVALID INPUT",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String nono =  myArray2[i] ;
                            set.setText( nono);
                        }}}
                }

            }
        });
        return V;
    }}





