package com.mojix;

import com.mojix.properties.LoadProperties;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paul Landaeta on 25/11/2015.
 */
public class    Main {
    public static void main(String args[]) throws IOException {
        String date="2015/13/01";
        String pattern="^\\d{4}/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])$";
        Pattern r = Pattern.compile(pattern);
        Matcher m=r.matcher(date);
        if(m.find()){
            System.out.println(m.group(0));
        }
        else
            System.out.println("no found");

    }
    public static boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");

        try {
            format.parse(input);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }
}
