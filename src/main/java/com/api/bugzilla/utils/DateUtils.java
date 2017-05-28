package com.api.bugzilla.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by springfield-home on 5/28/17.
 */
public class DateUtils {
    public static Date convertToDate(String sDate, String sPattern) {
        DateFormat format = new SimpleDateFormat(sPattern);
        try {
            Date date = format.parse(sDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date convertToDate(String sDate) {
        // ex :2017-05-26 13:38:57
        return convertToDate(sDate,"yyyy-MM-dd HH:mm:ss");
    }


}
