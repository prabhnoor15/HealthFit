package com.prabh.cheeku.healthfit.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.prabh.cheeku.healthfit.data.contract.CONTENT_AUTHORITY;
import static com.prabh.cheeku.healthfit.data.contract.PATH;
import static com.prabh.cheeku.healthfit.data.contract.PATH;

/**
 * Created by cheeku on 2017-08-30.
 */

public class provider extends ContentProvider {

    private static final int entry = 100;
    private static final int entry_id = 101;




            private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

            static {

                sUriMatcher.addURI(CONTENT_AUTHORITY, PATH, entry);
                sUriMatcher.addURI(CONTENT_AUTHORITY, PATH + "/#", entry_id);
            }


            private helper mDbHelper;
            @Override
            public boolean onCreate() {
                mDbHelper = new helper(getContext());
                return true;
            }

            @Nullable
            @Override
            public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
                SQLiteDatabase database = mDbHelper.getReadableDatabase();
                Cursor cursor;

                int match = sUriMatcher.match(uri);
                switch (match) {
                    case entry:
                        cursor = database.query(contract.cont.TABLE_NAME, projection, selection, selectionArgs,
                                null, null, sortOrder);
                        break;
                    case entry_id:

                        selection = contract.cont._ID + "=?";
                        selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                        cursor = database.query(contract.cont.TABLE_NAME, projection, selection, selectionArgs,
                                null, null, sortOrder);
                        break;
                    default:
                        throw new IllegalArgumentException("Cannot query unknown URI " + uri);
                }

                cursor.setNotificationUri(getContext().getContentResolver(), uri);

                return cursor;
            }




            @Nullable
            @Override
            public String getType(@NonNull Uri uri) {

                final int match = sUriMatcher.match(uri);
                switch (match) {
                    case entry:
                        return contract.cont.CONTENT_LIST_TYPE;
            case entry_id:
                return contract.cont.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
    }}

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case entry:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertPet(Uri uri, ContentValues values) {
        String name = values.getAsString(contract.cont.COLUMN_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Pet requires a name");
        }

        Integer gender = values.getAsInteger(contract.cont.COLUMN_GENDER);
        if (gender == null ) {
            throw new IllegalArgumentException("Pet requires valid gender");
        }

        Integer weight = values.getAsInteger(contract.cont.COLUMN_WEIGHT);
        if (weight != null && weight < 0) {
            throw new IllegalArgumentException("Pet requires valid weight");
        }


        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(contract.cont.TABLE_NAME, null, values);
        if (id == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }



    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case entry:
                rowsDeleted = database.delete(contract.cont.TABLE_NAME, selection, selectionArgs);
                break;
            case entry_id:
                selection = contract.cont._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(contract.cont.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
