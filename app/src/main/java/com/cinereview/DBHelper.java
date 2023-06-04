package com.cinereview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SearchHistoryDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "SearchHistory";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SEARCH_QUERY = "search_query";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SEARCH_QUERY + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertSearchQuery(String searchQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SEARCH_QUERY, searchQuery);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<String> getAllSearchQueries() {
        List<String> searchQueries = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int columnIndex = cursor.getColumnIndex(COLUMN_SEARCH_QUERY);
        if (cursor.moveToFirst()) {
            do {
                String searchQuery = cursor.getString(columnIndex);
                searchQueries.add(searchQuery);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return searchQueries;
    }
}
