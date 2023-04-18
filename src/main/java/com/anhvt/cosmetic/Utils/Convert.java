package com.anhvt.cosmetic.Utils;

import java.util.Date;

public class Convert {
    public static String DateToTimestamp(Date date){
        Long time = date.getTime()/1000;
        return time.toString();
    }

    public static String TimestampToDate(String unixTime){
        Date date = new Date(Long.parseLong(unixTime) * 1000L);
        String dateStr = String.format("%tF %<tT", date);
        return dateStr;
    }


}
