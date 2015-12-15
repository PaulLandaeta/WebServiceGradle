package com.mojix.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paul Landaeta on 15/12/2015.
 */
public class DateUtil {
    private String date;
    private String serialDate;
    private Date startDate;
    public DateUtil(String date){
        this.date=date;
    }
    public boolean isValidDate(){
        String pattern="^\\d{4}\\/(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])$";
        Pattern regex = Pattern.compile(pattern);
        Matcher match=regex.matcher(date);
        if(match.find())
            return true;
        return false;
    }
    public String getSerialDate(){
        serialDate="INV";
        StringTokenizer token=new StringTokenizer(date,"/");
        while(token.hasMoreElements())
            serialDate+=token.nextToken();
        return serialDate;
    }

    public Date getStartDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        startDate = formatter.parse(date);
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

