package com.androidtesttodo.androidtest.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidtesttodo.androidtest.App;
import com.androidtesttodo.androidtest.R;
import com.androidtesttodo.androidtest.adapter.TodoAdapter;
import com.androidtesttodo.androidtest.dialog.AddItemDialog;
import com.androidtesttodo.androidtest.model.TodoItem;

public class MainActivity extends AppCompatActivity implements AddItemDialog.TodoSaveListener{

    private static final String ADD_ITEM_DIALOG = "dialog_tag";

    private TodoAdapter mAdapter;
    private ListView mListViewTodoItems;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUI();
        setListeners();
    }

    private void createUI() {
        mListViewTodoItems = (ListView) findViewById(R.id.listView_test);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.button_add_item);
        mAdapter = new TodoAdapter(this, R.layout.list_item_todo, App.getDataManager().getTodoItems());
    }

    private void setListeners() {
        mListViewTodoItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(App.getDataManager().getTodoItems().get(position));
            }
        });
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        mListViewTodoItems.setAdapter(mAdapter);
    }

    private void showDialog(TodoItem todoItem) {
        AddItemDialog dialog = AddItemDialog.newInstance(todoItem);
        dialog.show(getSupportFragmentManager(), ADD_ITEM_DIALOG);
    }

    private void showDialog() {
        AddItemDialog dialog = AddItemDialog.newInstance();
        dialog.show(getSupportFragmentManager(), ADD_ITEM_DIALOG);
    }

    @Override
    public void onTodoItemSaved() {
        mAdapter.notifyDataSetChanged();
    }
}
