package com.iuh.mobile_lab07.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.iuh.mobile_lab07.entity.Location;

import java.util.List;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Insert
    void insert(Location location);

    @Query("DELETE FROM location WHERE name = :name")
    void delete(String name);
}
