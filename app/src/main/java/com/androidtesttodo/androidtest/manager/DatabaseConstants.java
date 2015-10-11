package com.androidtesttodo.androidtest.manager;

/**
 * Created by andrewkhristyan on 10/11/15.
 */
public class DatabaseConstants {

    public static final String DATABASE_NAME = "data_base_todo";
    public static final int DATABASE_VERSION = 1;


    public static class Tables {
        public static String TODO_TABLE = "todo_table";
    }

    public static class TodoTable {
        public static String TODO_ID = "_id";
        public static String TODO_NAME = "todo_name";
        public static String TODO_DESCRIPTION = "todo_description";
        public static String TODO_IS_CHECKED = "todo_is_checked";
    }
}
