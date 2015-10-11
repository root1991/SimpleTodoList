package com.androidtesttodo.androidtest.model;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class TodoItem {

    private long mId;
    private String mDescription;
    private String mName;
    private boolean mChecked;

    public TodoItem(long id, String description, String name, boolean checked) {
        mId = id;
        mDescription = description;
        mName = name;
        mChecked = checked;
    }

    public TodoItem() {
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    public long getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setId(long id) {
        mId = id;
    }

}
