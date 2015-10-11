package com.androidtesttodo.androidtest.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class TodoSqliteOpenHelper extends SQLiteOpenHelper{

    private static final String CREATE_TABLE_FEEDS = "CREATE TABLE " + DatabaseConstants.Tables.TODO_TABLE + "("
            + DatabaseConstants.TodoTable.TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DatabaseConstants.TodoTable.TODO_NAME + " TEXT, "
            + DatabaseConstants.TodoTable.TODO_DESCRIPTION + " TEXT, "
            + DatabaseConstants.TodoTable.TODO_IS_CHECKED + " INTEGER, "
            + " UNIQUE (" + DatabaseConstants.TodoTable.TODO_NAME + ") ON CONFLICT REPLACE"
            + ")";

    public TodoSqliteOpenHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null,
                DatabaseConstants.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FEEDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
