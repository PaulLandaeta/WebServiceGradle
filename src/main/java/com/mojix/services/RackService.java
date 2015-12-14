package com.mojix.services;

import com.google.gson.Gson;
import com.mojix.model.rack.Racks;
import com.mojix.properties.PropertiesController;
import com.mojix.restClient.RestClientPost;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paul Landaeta on 11/12/2015.
 */
public class RackService {
    private RestClientPost restclient;
    private PropertiesController propertiesController;
    private static String STARTDATE="startDate=";
    private static String ENDDATE="&endDate=";
    private static String TS="&ts=";
    public Racks execute(Date startDate,Date endDate) throws IOException {
        propertiesController = new PropertiesController();
        System.out.println(this.getEndPoint(startDate, endDate));
        System.out.println(this.getBody());
        Gson gson=new Gson();
        Map<String,String> body=new HashMap<>();
        body.put("826","1");
        body.put("827","1");
        String json=gson.toJson(body);
        System.out.println(json);

        restclient = new RestClientPost(this.getEndPoint(startDate,endDate),json);
        String output = restclient.execute();
        System.out.println(output+"asdasd");
        Racks racks = new Racks();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            racks = mapper.readValue(output, Racks.class);
        }
        return racks;
    }

    public String getEndPoint(Date start,Date end){
        Date currentDate=new Date();
        String params=STARTDATE+start.getTime()+ENDDATE+end.getTime()+TS+currentDate.getTime();
        return propertiesController.getEndPointRacks()+params;
    }
    public String getBody(){
        return propertiesController.getBodyRacks();
    }
}
