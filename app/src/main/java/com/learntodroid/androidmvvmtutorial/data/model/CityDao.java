package com.learntodroid.androidmvvmtutorial.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDao {
    @Insert
    void insert(City city);

    @Delete
    void delete(City city);

    @Query("SELECT * FROM city_table")
    LiveData<List<City>> getFavouriteCities();

    @Query("DELETE FROM city_table")
    void deleteAll();
}
