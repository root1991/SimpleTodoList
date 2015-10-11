package com.androidtesttodo.androidtest.manager;

import android.content.Context;

import com.androidtesttodo.androidtest.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class CacheManager {

    private Context mContext;
    private List<TodoItem> mTodoItems;

    void init(Context context) {
        mContext = context;
        mTodoItems = new ArrayList<>();
    }

    void saveTodoItem(TodoItem todoItem) {
        for (int i = 0; i < mTodoItems.size(); i++) {
            if (todoItem.getId() == mTodoItems.get(i).getId()) {
                mTodoItems.set(i, todoItem);
                return;
            }
        }
        mTodoItems.add(todoItem);
    }


    void removeTodoItem(TodoItem todoItem) {
        mTodoItems.remove(todoItem);
    }

    void clearTodoItems() {
        mTodoItems.clear();
    }

    List<TodoItem> getTodoItems() {
        return mTodoItems;
    }

    void saveTodoItems(List<TodoItem> todoItems) {
        mTodoItems.addAll(todoItems);
    }
}
