package com.vaidoos.eventbusdemo.networking.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
    public String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR) ;
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        return formatDate(year,month+1,dayOfMonth);
    }

    public String formatDate(int year, int month, int dayOfMonth){
        String m = "";
        String day = "";
        String date = "";
        if (month<=9){
            m = "0"+month;
        }

        if (dayOfMonth<=9){
            day = "0"+dayOfMonth;
        }

        if (month<=9 && dayOfMonth<=9){
            date = day +"/"+ m +"/"+ year;
        }else if (month<=9){
            date = dayOfMonth +"/"+ m +"/"+ year;
        }else if (dayOfMonth<=9){
            date = day +"/"+ month +"/"+ year;
        } else {
            date = dayOfMonth +"/"+ month +"/"+ year;
        }
        return date;
    }

    public String stringToDateConverter(String date){
        //Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        Date dateParse = null;
        try {
            dateParse = df2.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.setTime(dateParse);

        int year = c.get(Calendar.YEAR) ;
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String dd;
        String mm;
        if (day<10 && (month+1)<10){
            dd = "0"+day;
            mm = "0"+(month+1);
            return dd+"/"+mm+"/"+year;
        }

        if (day<10 && (month+1)>9){
            dd = "0"+day;
            //Log.d("digit",""+dd+" "+(month+1)+" "+year);
            return dd+"/"+(month+1)+"/"+year;
        }

        if (day>9 && (month+1)<9){
            mm = "0"+(month+1);
            //Log.d("digit",""+day+" "+mm+" "+year);
            return day+"/"+mm+"/"+year;
        }

        if (day>9 && (month+1)>9){
            return day+"/"+(month+1)+"/"+year;
        }

        return null;

    }

}
