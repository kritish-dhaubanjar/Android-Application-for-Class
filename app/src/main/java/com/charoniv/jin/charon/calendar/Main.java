package com.charoniv.jin.charon.calendar;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String [] args){
        NepaliCalendar nepaliCalendar = new NepaliCalendar();


        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        NepaliCalendar.NepaliDate nepaliDate = nepaliCalendar.engToNep(year, month+2, day);
        System.out.println(nepaliDate);
//
        System.out.println(nepaliCalendar.getTotalDaysInNepaliMonth(nepaliDate.year, nepaliDate.month));

        for(int i = 1; i<=nepaliCalendar.getTotalDaysInNepaliMonth(nepaliDate.year, nepaliDate.month); i++){
            System.out.println(i);
        }


    }

}
