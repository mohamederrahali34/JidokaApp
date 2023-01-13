package com.nimbleways.jidokabot.utils;

public class TimeUnitConverterUtils {
    public static int hoursToMills(final int hours){
        return hours*3600*1000;
    }
    public static int minutesToMills(final int minutes){
        return minutes*60*1000;
    }
}
