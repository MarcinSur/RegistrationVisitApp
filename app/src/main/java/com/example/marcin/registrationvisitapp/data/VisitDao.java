package com.example.marcin.registrationvisitapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VisitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertVisit(Visit visit);

    @Update()
    void updateVisit(Visit visit);

    @Delete()
    void deleteVisit(Visit visit);

    @Query("SELECT * FROM visits")
    LiveData<List<Visit>> loadAllVisits();

}
