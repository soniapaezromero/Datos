package com.example.gestiondedatos.Ejercicio4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.Nullable;

public class WebsProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.gestiondedatos.Ejercicio4";
    private static final String BASE_PATH = "websList";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final int WEBS = 1;
    private static final int ID = 2;

    private boolean firstTimeLoaded = false;


    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, WEBS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ID);
    }
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        WebsHelper helper = new WebsHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case WEBS:
                cursor = database.query(WebsHelper.NOMBRE_TABLA, WebsHelper.ALL_COLUMNS,
                        s, null, null, null, null);


                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case WEBS:
                return "vnd.android.cursor.dir";
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
    }




    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = database.insert(WebsHelper.NOMBRE_TABLA, null, contentValues);

        if (id > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);

            return _uri;
        }
        throw new SQLException("Insertion Failed for URI :" + uri);

    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int delCount = 0;
        switch (uriMatcher.match(uri)) {
            case WEBS:
                delCount = database.delete(WebsHelper.NOMBRE_TABLA, s, strings);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return delCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updCount = 0;
        switch (uriMatcher.match(uri)) {
            case WEBS:
                updCount = database.update(WebsHelper.NOMBRE_TABLA, contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updCount;
    }

}

