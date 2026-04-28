package com.example.callaccounting;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Abonent.class, City.class, Call.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AbonentDao abonentDao();
    public abstract CityDao cityDao();
    public abstract CallDao callDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "call_accounting.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}