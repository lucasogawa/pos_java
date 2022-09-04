package com.ogawalucas.automobilesupplycontrol.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ogawalucas.automobilesupplycontrol.automobile.dao.AutomobileDao;
import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;
import com.ogawalucas.automobilesupplycontrol.supply.dao.SupplyDao;
import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;

@androidx.room.Database(entities = {Automobile.class, Supply.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract AutomobileDao automobileDao();

    public abstract SupplyDao supplyDao();

    private static Database instance;

    public static Database get(final Context context) {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        Database.class,
                        "automobileSupplyControl.db"
                    ).allowMainThreadQueries().build();
                }
            }
        }

        return instance;
    }
}
