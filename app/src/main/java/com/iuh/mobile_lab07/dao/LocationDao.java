package com.iuh.mobile_lab07.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.iuh.mobile_lab07.entity.Location;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Location location);

    @Delete
    void delete(Location location);

    @Query("UPDATE location SET name = :name WHERE ID = :ID")
    void update(int ID, String name);

    @Delete
    void reset(List<Location> locationList);
}
