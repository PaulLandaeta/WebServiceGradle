package com.mojix.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by aquiroz on 10/2/2015.
 */
public class LoadProperties {

    private static final String ROUTE_PROPERTIES="/project/project.properties";
    private static Properties props;
    private static LoadProperties instance;

    private LoadProperties(){
        readProperties();
    }

    public static LoadProperties getInstance(){
        if (instance == null){
            instance = new LoadProperties();
        }
        return instance;
    }

    private static void readProperties(){
        props=new Properties();
        try {
            String home = System.getProperty("catalina.home");
            System.out.println("Cargando Archivo de properties");
            System.out.println(home);
            FileInputStream fis=new FileInputStream(new File(home+ROUTE_PROPERTIES));
            props.load(fis);
            System.out.println("Archivo de propiedades con exito.");
        }catch (IOException e){
            props=null;
            System.out.println("Error Load file properties");

        }
    }

    public String getProperty(String key) {
        String value = null;
     //   System.out.println(key);
    //    System.out.println(props);
        if (props.containsKey(key)){
            value = (String) props.get(key);
        }
        else{
            System.out.println("Propiedad ["+key +"] no encontrada");
        }
        return value;
    }
}
