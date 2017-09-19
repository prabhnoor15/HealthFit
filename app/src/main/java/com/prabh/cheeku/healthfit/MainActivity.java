package com.prabh.cheeku.healthfit;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.prabh.cheeku.healthfit.data.contract;


public class MainActivity extends AppCompatActivity  implements
        LoaderManager.LoaderCallbacks<Cursor>{

public int x;
    public static double choice ;

    private static final int ENTRY_LOADER = 0;

    com.prabh.cheeku.healthfit.cursor mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
    }


    public void  Order(View v){
       double k =  com.prabh.cheeku.healthfit.cursor.getHealth() ;
        if(k > 50 ){
            Intent intent = new Intent(MainActivity.this, com.prabh.cheeku.healthfit.third.class);
            startActivity(intent);
        }

         else{
        Intent intent = new Intent(MainActivity.this, four.class);
        startActivity(intent);
    }}


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                Intent intent = new Intent(MainActivity.this, sec.class);
                startActivity(intent);
                return true;
            case R.id.action_delete_all_entries:
                if(x >= 1) {
                    Log.d("ADebugTag", "Value: " + Float.toString(x));
                    showDeleteConfirmationDialog();

                    return true;
                }
                else{
                    Toast.makeText(this, "No Entries",
                            Toast.LENGTH_SHORT).show();
                }

        }
        return super.onOptionsItemSelected(item);
    }
    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("All Entries Will Be Deleted?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getContentResolver().delete(contract.cont.CONTENT_URI, null, null);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
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
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                contract.cont._ID,
                contract.cont.COLUMN_RACE,
                contract.cont.COLUMN_HEIGHT,
                contract.cont.COLUMN_WEIGHT};


        return new CursorLoader(this,   // Parent activity context
                contract.cont.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);// Default sort order

    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }


   public void displayDatabaseInfo() {



       String[] projection = {
               contract.cont._ID,
               contract.cont.COLUMN_RACE,
               contract.cont.COLUMN_NAME,
               contract.cont.COLUMN_HEIGHT,
               contract.cont.COLUMN_AGE,
               contract.cont.COLUMN_WEIGHT};



       final Cursor cursor = getContentResolver().query(
               contract.cont.CONTENT_URI,   // The content URI of the words table
               projection,             // The columns to return for each row
               null,                   // Selection criteria
               null,                   // Selection criteria
               null);                  // The sort order for the returned rows
       x = cursor.getCount();

       ListView eListView = (ListView) findViewById(R.id.list);

       View emptyView = findViewById(R.id.empty_view);
       eListView.setEmptyView(emptyView);


       // Setup an Adapter to create a list item for each row
       cursor adapter = new cursor(this, cursor);


       eListView.setAdapter(adapter);

       eListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               int idColumnIndex = cursor.getColumnIndex(contract.cont._ID);
               int nameColumnIndex = cursor.getColumnIndex(contract.cont.COLUMN_WEIGHT);
               int name = cursor.getColumnIndex(contract.cont.COLUMN_AGE);
               int currentWeight = cursor.getInt(nameColumnIndex);
                   int currentID = cursor.getInt(idColumnIndex);
               choice = cursor.getDouble(name);

                       Intent intent = new Intent(MainActivity.this, four.class);
                       Uri currentPetUri = ContentUris.withAppendedId(contract.cont.CONTENT_URI, id);
                       intent.setData(currentPetUri);
                       startActivity(intent);

           }
       });
   }


}