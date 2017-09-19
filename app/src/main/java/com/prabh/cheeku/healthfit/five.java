package com.prabh.cheeku.healthfit;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


public class five extends AppCompatActivity {
    /** URL for earthquake data from the USGS dataset */

    public static final String LOG_TAG = five.class.getSimpleName();
    public String jav;
    public static String lipid = "";
    public static String energy = "";
    public static String carbo = "";
    public static String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MainActivity.choice > 24.9 || MainActivity.choice < 18.5) {
            setTheme(R.style.you);
        }
        if(MainActivity.choice <= 24.9 &&MainActivity.choice >= 18.5) {
            setTheme(R.style.you2);
        }
        setContentView(R.layout.activity_five2);
        TsunamiAsyncTask task = new TsunamiAsyncTask();
        task.execute();

    }


    private class TsunamiAsyncTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl( "https://api.nal.usda.gov/ndb/search/?format=json&q=" + calorie_2.fin +
                    "&sort=n&max=1&offset=0&api_key=M7fLgYN4At4iRF3Ve37rLzfBgOlUMpwDBJFlSeeZ");

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
               String DD = extractFeatureFromJson(jsonResponse);
            jav = DD;
            return DD;
        }

        @Override
        protected void onPostExecute(String e) {
            Tsunami task = new Tsunami();
            task.execute();
        }



        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }


        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private String extractFeatureFromJson(String anss) {
            try {
                JSONObject baseJsonResponse = new JSONObject(anss);
                JSONObject base = baseJsonResponse.getJSONObject("list");
                JSONArray Array = base.getJSONArray("item");
                JSONObject ndf = Array.getJSONObject(0);
                String gun = ndf.getString("ndbno");
                name = ndf.getString("name");
                return gun;
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }}

    public void updateUi(String s){

        TextView dateTextView = (TextView) findViewById(R.id.prot);
        TextView FATTY = (TextView) findViewById(R.id.fat);
        TextView ener = (TextView) findViewById(R.id.energy);
        TextView power = (TextView) findViewById(R.id.carb);
       TextView new_name = (TextView) findViewById(R.id.source);
      //  TextView new_name2 = (TextView) findViewById(R.id.weight2);

        // TextView new_name2 = (TextView) findViewById(R.id.sou);

       // String[] arr = name.split(",");

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
      //  new_name2.setText("** Values per 100g");
        power.setText(carbo);
        FATTY.setText(lipid);
        ener.setText(energy);
        dateTextView.setText(s);
        if(!calorie_2.fin.equals("")) {
            new_name.setText(name);
        }
        //new_name2.setText(arr[2]);

    }

    private class Tsunami extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = createUrl
                    ("https://api.nal.usda.gov/ndb/V2/reports?ndbno=" +jav+"&type=b&format=json&api_key=M7fLgYN4At4iRF3Ve37rLzfBgOlUMpwDBJFlSeeZ");

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }
            String sq = extractFeatureFrom(jsonResponse);
            return sq;
        }

        @Override
        protected void onPostExecute(String e) {
            updateUi(e);
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }


        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private String extractFeatureFrom(String anss) {
            try {
                JSONObject Response = new JSONObject(anss);
                //JSONObject base = baseJsonResponse.getJSONObject("list");
                JSONArray Array2 = Response.getJSONArray("foods");
                JSONObject nda = Array2.getJSONObject(0);
                JSONObject nd = nda.getJSONObject("food");
                JSONArray Array3= nd.getJSONArray("nutrients");
                int j = Array3.length();
                JSONObject nda2 ;
                String don = "";

                for (int i = 0 ; i < j ; i++){
                    nda2 =  Array3.getJSONObject(i);
                    if(nda2.getString("nutrient_id").equals("203")){
                         don = nda2.getString("value");
                    }
                }
                for (int i = 0 ; i < j ; i++){
                    nda2 =  Array3.getJSONObject(i);
                    if(nda2.getString("nutrient_id").equals("204")){
                        lipid = nda2.getString("value");
                    }

                }
                for (int i = 0 ; i < j ; i++){
                    nda2 =  Array3.getJSONObject(i);
                    if(nda2.getString("nutrient_id").equals("208")){
                        energy = nda2.getString("value");
                    }

                }
                for (int i = 0 ; i < j ; i++){
                    nda2 =  Array3.getJSONObject(i);
                    if(nda2.getString("nutrient_id").equals("205")){
                        carbo = nda2.getString("value");
                    }

                }
                return don;
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }}

}
