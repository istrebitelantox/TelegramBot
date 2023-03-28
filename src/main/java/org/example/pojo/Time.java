package org.example.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time {
    private long start;
    private long stop;
    private int duration;
    private int minDuration;
    private int maxDuration;
    private int sumDuration;
    private String parseDate(long sec){
        DateFormat obj = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date res=new Date(sec);
        return obj.format(res);
    }
    @Override
    public String toString() {
        String newStart=parseDate(start);
        String newStop=parseDate(stop);
        long newDuration= TimeUnit.MILLISECONDS.toSeconds(duration);
        return "" +
                "\n\t\t🛫start=" + newStart +
                ",\n\t\t🛬stop=" + newStop +
                ",\n\t\t⌚duration=" + newDuration+" sec";
    }
}
