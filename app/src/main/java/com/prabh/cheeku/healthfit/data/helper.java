package com.prabh.cheeku.healthfit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by cheeku on 2017-08-30.
 */

public class helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;
    public helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {



        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + contract.cont.TABLE_NAME+ " (" +
        contract.cont._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                contract.cont.COLUMN_NAME + " TEXT NOT NULL, " +
        contract.cont.COLUMN_GENDER + " INTEGER NOT NULL, " +
                contract.cont.COLUMN_WEIGHT + " REAL NOT NULL, " +
                contract.cont.COLUMN_HEIGHT + " REAL NOT NULL, " +
                contract.cont.COLUMN_RACE + " INTEGER NOT NULL, " +
                contract.cont.COLUMN_AGE + " REAL NOT NULL);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
