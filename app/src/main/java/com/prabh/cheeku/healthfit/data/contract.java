package com.prabh.cheeku.healthfit.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by cheeku on 2017-08-30.
 */

public class contract {
    private contract(){}



    public static final String CONTENT_AUTHORITY = "com.prabh.cheeku.healthfit";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);





    public static final String PATH = "healthfit";
    public static final class cont implements BaseColumns{


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH);


        public final static String TABLE_NAME = "bmi";


        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME ="name";
        public final static String COLUMN_GENDER = "gender";
        public final static String COLUMN_RACE = "ethinicity";
        public final static String COLUMN_HEIGHT = "height";
        public final static String COLUMN_WEIGHT = "weight";
        public final static String COLUMN_AGE = "age";

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;



    }


}
