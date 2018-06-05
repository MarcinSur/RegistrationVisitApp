package com.example.marcin.registrationvisitapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface VisitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertVisit(Visit visit);

    @Update()
    public void updateVisit(Visit visit);

    @Delete()
    public  void deleteVisit(Visit visit);

    @Query("SELECT * FROM visits")
    public LiveData<List<Visit>> loadAllVisits();

}
