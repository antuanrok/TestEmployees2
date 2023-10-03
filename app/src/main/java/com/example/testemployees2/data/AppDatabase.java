package com.example.testemployees2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testemployees2.pojo.Person;

import kotlin.jvm.Synchronized;

@Database(entities = {Person.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends  RoomDatabase{
    private static AppDatabase database;
    private static final String DB_NAME = "persons.db";

    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
            return database;
        }
    }

    public abstract PersonDao personDao();
}
