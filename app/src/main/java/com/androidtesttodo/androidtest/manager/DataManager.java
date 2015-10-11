package com.androidtesttodo.androidtest.manager;

import android.content.Context;

import com.androidtesttodo.androidtest.model.TodoItem;

import java.util.List;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class DataManager {

    private CacheManager mCacheManager = new CacheManager();
    private DbManager mDbManager = new DbManager();

    public void init(Context context) {
        mCacheManager.init(context);
        mDbManager.init(context);
    }

    public List<TodoItem> getTodoItems() {
        List<TodoItem> items = mCacheManager.getTodoItems();
        if (items.isEmpty()) {
            items.addAll(mDbManager.getTodoItems());
        }
        return items;
    }

    public void saveTodoItem(TodoItem todoItem) {
        long id =  mDbManager.saveTodoItem(todoItem);
        todoItem.setId(id);
        mCacheManager.saveTodoItem(todoItem);
    }


}
