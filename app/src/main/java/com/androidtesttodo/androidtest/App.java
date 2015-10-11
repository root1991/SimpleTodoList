package com.androidtesttodo.androidtest;

import android.app.Application;

import com.androidtesttodo.androidtest.manager.DataManager;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class App extends Application {

    private static DataManager dataManager = new DataManager();

    @Override
    public void onCreate() {
        super.onCreate();
        dataManager.init(this);
    }

    public static DataManager getDataManager() {
        return dataManager;
    }
}
