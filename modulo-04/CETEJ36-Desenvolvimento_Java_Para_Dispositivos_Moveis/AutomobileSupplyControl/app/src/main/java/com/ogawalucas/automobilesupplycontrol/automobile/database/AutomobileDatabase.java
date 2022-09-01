package com.ogawalucas.automobilesupplycontrol.automobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;

@Database(entities = {Automobile.class}, version = 1, exportSchema = false)
public abstract class AutomobileDatabase extends RoomDatabase {

    public abstract AutomobileDao automobileDao();

    private static AutomobileDatabase instance;

    public static AutomobileDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AutomobileDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AutomobileDatabase.class,
                        "automobile.db"
                    ).allowMainThreadQueries().build();
                }
            }
        }

        return instance;
    }
}
