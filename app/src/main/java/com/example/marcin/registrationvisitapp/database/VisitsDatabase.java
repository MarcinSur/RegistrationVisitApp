package com.example.marcin.registrationvisitapp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.marcin.registrationvisitapp.data.Visit;
import com.example.marcin.registrationvisitapp.data.VisitDao;
import com.google.firebase.database.ChildEventListener;

@Database(entities = {Visit.class}, version = 1)
public abstract class VisitsDatabase extends RoomDatabase {

    public abstract VisitDao visitDao();

    private static VisitsDatabase INSTANCE;
    public static VisitsDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VisitsDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            VisitsDatabase.class,
                            "visit_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

