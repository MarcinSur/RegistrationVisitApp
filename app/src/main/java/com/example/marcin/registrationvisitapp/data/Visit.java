package com.example.marcin.registrationvisitapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;

@Entity(tableName = "visits")
public class Visit {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String details;
    private Date date;
    private Timestamp timestamp;
}
