package com.example.myapplication1.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Converter {

    public static Date convertDate(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date =null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
    public static Long convertDateTimestamp(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
             date = dateFormat.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date.getTime();
    }
}