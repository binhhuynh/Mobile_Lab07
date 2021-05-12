package com.iuh.mobile_lab07.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.iuh.mobile_lab07.entity.User;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insert(User user);

    @Query("DELETE FROM user WHERE name = :name")
    void delete(String name);
}
