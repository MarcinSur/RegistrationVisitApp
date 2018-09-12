package com.example.marcin.registrationvisitapp.ui.visit;

import com.example.marcin.registrationvisitapp.utilities.converter.DateConverter;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "visits")
public class Visit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String details;
    @TypeConverters(DateConverter.class)
    private Date date;
    private String pushKey;
    private long timeStamp;

    public static  DiffUtil.ItemCallback<Visit> DIFF_CALLBACK ;

    public Visit(){
        // Default constructor required for calls to DataSnapshot.getValue(Visit.class)
    }

    public Visit(int id, String name, String details, Date date) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.date = date;
    }
    public Visit(int id, String name, String details, long timeStamp) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.timeStamp = timeStamp;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }
}
