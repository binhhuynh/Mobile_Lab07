package com.iuh.mobile_lab07.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.iuh.mobile_lab07.dao.LocationDao;
import com.iuh.mobile_lab07.entity.Location;

@Database(entities = {Location.class}, version=1)
public abstract class TravelDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
}
