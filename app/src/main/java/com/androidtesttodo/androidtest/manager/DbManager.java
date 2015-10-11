package com.androidtesttodo.androidtest.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtesttodo.androidtest.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class DbManager {

    private SQLiteDatabase mDataBase;

    void init(Context context) {
        mDataBase = new TodoSqliteOpenHelper(context).getWritableDatabase();
    }

    List<TodoItem> getTodoItems() {
        Cursor c = mDataBase.query(DatabaseConstants.Tables.TODO_TABLE, null, null, null, null, null, null);
        return select(c);
    }

    long saveTodoItem(TodoItem todoItem) {
       return mDataBase.insert(DatabaseConstants.Tables.TODO_TABLE, null, todoItemsToCv(todoItem));
    }

    private List<TodoItem> select(Cursor c) {
        final List<TodoItem> events = new ArrayList<>();
        try {
            if (c.moveToFirst()) {
                do {
                    events.add(parseEvent(c));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return events;
    }

    private TodoItem parseEvent(Cursor c) {
        long id = c.getLong(c.getColumnIndex(DatabaseConstants.TodoTable.TODO_ID));
        String name = c.getString(c.getColumnIndex(DatabaseConstants.TodoTable.TODO_NAME));
        String description = c.getString(c.getColumnIndex(DatabaseConstants.TodoTable.TODO_DESCRIPTION));
        boolean isChecked = c.getInt(c.getColumnIndex(DatabaseConstants.TodoTable.TODO_IS_CHECKED)) != 0;
        return new TodoItem(id, description, name, isChecked);
    }

    private ContentValues todoItemsToCv(TodoItem todoItem) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseConstants.TodoTable.TODO_NAME, todoItem.getName());
        cv.put(DatabaseConstants.TodoTable.TODO_DESCRIPTION, todoItem.getDescription());
        cv.put(DatabaseConstants.TodoTable.TODO_IS_CHECKED, todoItem.isChecked());
        return cv;
    }

}
