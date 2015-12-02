package com.mojix;

import com.mojix.facade.ConsumServices;
import java.io.IOException;

/**
 * Created by Paul Landaeta on 25/11/2015.
 */
public class Main {
    public static void main(String args[]) throws IOException {

        ConsumServices consumServices=new ConsumServices();
        consumServices.getTagsFound();
    }
}
