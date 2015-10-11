package com.androidtesttodo.androidtest.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.androidtesttodo.androidtest.App;
import com.androidtesttodo.androidtest.R;
import com.androidtesttodo.androidtest.model.TodoItem;

import java.util.List;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class TodoAdapter extends ArrayAdapter<TodoItem> {

    public TodoAdapter(Context context, int resource, List<TodoItem> todoItems) {
        super(context, resource, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_todo, parent, false);
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final TodoItem todoItem = getItem(position);
        underlineText(todoItem.isChecked(), viewHolder.textViewDescription, getItem(position));
        viewHolder.checkBoxItem.setChecked(todoItem.isChecked());
        viewHolder.checkBoxItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoItem.setChecked(viewHolder.checkBoxItem.isChecked());
                underlineText(viewHolder.checkBoxItem.isChecked(), viewHolder.textViewDescription, todoItem);
                App.getDataManager().saveTodoItem(todoItem);
            }
        });
        return convertView;
    }

    private void underlineText(boolean needUnderline, TextView textViewName, TodoItem todoItem) {
        if (needUnderline) {
            SpannableString content = new SpannableString(todoItem.getName());
            content.setSpan(new StrikethroughSpan(), 0, content.length(), 0);
            textViewName.setText(content);
        } else {
            textViewName.setText(todoItem.getName());
        }
    }


    static class ViewHolder {
        TextView textViewDescription;
        CheckBox checkBoxItem;

        void init(View view) {
            textViewDescription = (TextView) view.findViewById(R.id.text_view_description);
            checkBoxItem = (CheckBox) view.findViewById(R.id.checkbox_item);
        }
    }
}
