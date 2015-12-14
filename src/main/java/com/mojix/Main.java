package com.mojix;

import com.mojix.properties.LoadProperties;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Paul Landaeta on 25/11/2015.
 */
public class    Main {
    public static void main(String args[]) throws IOException {

        try{
            System.out.println(isValidDate("2015/5454/13"));
        }
        catch(Exception e){
            System.out.println("asdasd");
        }

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
