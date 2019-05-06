package com.example.rovermore.weatherapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

@Dao
public interface LocationDao {

        @Query("SELECT * FROM locations ")
        LiveData<List<Location>> loadAllLocation();

        @Insert
        void insertLocation(Location Location);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateLocation(Location Location);

        @Delete
        void deleteLocation(Location Location);

        /*@Query("DELETE FROM locations WHERE idDatabase = :idDatabase")
        int deleteBydbId(int idDatabase);

        @Query("SELECT * FROM locations WHERE idDatabase = :idDatabase")
        LiveData<Location> loadLocationById(int idDatabase);*/

        @Query("SELECT * FROM locations WHERE keyLocation = :keyLocation")
        Location loadLocationByKey(int keyLocation);
    }

