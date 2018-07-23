package com.example.marcin.registrationvisitapp.utilities.converter;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }
    @TypeConverter
    public static Long tiTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
