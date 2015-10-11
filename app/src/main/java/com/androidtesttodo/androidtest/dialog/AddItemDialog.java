package com.androidtesttodo.androidtest.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.androidtesttodo.androidtest.App;
import com.androidtesttodo.androidtest.R;
import com.androidtesttodo.androidtest.model.TodoItem;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class AddItemDialog extends DialogFragment {

    private EditText mEditTextDescription;
    private EditText mEditTextName;
    private Button mButtonSave;
    private TodoItem mTodoItem;
    private View view;
    private TodoSaveListener mTodoSaveListener;

    public static AddItemDialog newInstance(TodoItem todoItem) {
        AddItemDialog dialog = new AddItemDialog();
        dialog.mTodoItem = todoItem;
        return dialog;
    }

    public static AddItemDialog newInstance() {
        return new AddItemDialog();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mTodoSaveListener = (TodoSaveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_add_item, container, false);
        createUI(view);
        bind(mTodoItem);
        return view;
    }

    private void createUI(View view) {
        mEditTextDescription = (EditText) view.findViewById(R.id.edit_text_descriprion);
        mEditTextName = (EditText) view.findViewById(R.id.edit_text_name);
        mButtonSave = (Button) view.findViewById(R.id.button_save);
    }

    private void bind(TodoItem todoItem) {
        if (todoItem != null) {
            mEditTextDescription.setText(todoItem.getDescription());
            mEditTextName.setText(todoItem.getName());
        }
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTodoItem();
                mTodoSaveListener.onTodoItemSaved();
            }
        });
    }

    private void saveTodoItem() {
        if (mTodoItem == null) {
            mTodoItem = new TodoItem();
        }
        if (!TextUtils.isEmpty(mEditTextName.getText().toString())) {
            mTodoItem.setName(mEditTextName.getText().toString());
            mTodoItem.setDescription(mEditTextDescription.getText().toString());
            App.getDataManager().saveTodoItem(mTodoItem);
            dismiss();
        } else {
            mEditTextName.setError("Name should be not empty!!");
        }
    }

    public interface TodoSaveListener {

        void onTodoItemSaved();

    }
}
