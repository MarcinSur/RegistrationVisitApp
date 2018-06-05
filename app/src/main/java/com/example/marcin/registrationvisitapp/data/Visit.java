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

    public Visit(int id, String name, String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {

        return details;
    }
}
