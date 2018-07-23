package com.example.marcin.registrationvisitapp.data;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;

@Entity(tableName = "visits")
public class Visit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String details;

    public static  DiffUtil.ItemCallback<Visit> DIFF_CALLBACK ;

    public Visit(){
        // Default constructor required for calls to DataSnapshot.getValue(Visit.class)
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
