package com.example.marcin.registrationvisitapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.marcin.registrationvisitapp.data.Visit;
import com.example.marcin.registrationvisitapp.data.VisitDao;

@Database(entities = {Visit.class}, version = 1)
public abstract class VisitsDatabase extends RoomDatabase {

    private static VisitsDatabase INSTANCE;
    public static VisitsDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VisitsDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            VisitsDatabase.class,
                            "visit_dayabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract VisitDao visitDao();
}
