package com.iuh.mobile_lab07.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.iuh.mobile_lab07.dao.LocationDao;
import com.iuh.mobile_lab07.entity.Location;

@Database(entities = {Location.class}, version=1, exportSchema = false)
public abstract class TravelDatabase extends RoomDatabase {

    private static TravelDatabase database;

    private static String DATABASE_NAME = "travelDB";

    public synchronized static TravelDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), TravelDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public abstract LocationDao locationDao();
}
