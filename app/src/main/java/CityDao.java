package com.example.callaccounting;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CityDao {
    @Insert
    void insert(City city);

    @Update
    void update(City city);

    @Delete
    void delete(City city);

    @Query("SELECT * FROM cities ORDER BY name")
    List<City> getAll();

    @Query("SELECT * FROM cities WHERE id = :id")
    City getById(int id);
}