package com.mojix.services;

import com.google.gson.Gson;
import com.mojix.model.Pipe.Pipe;
import com.mojix.model.Pipe.Pipes;
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
public class PipesService {
    private RestClientPost restclient;
    private PropertiesController propertiesController;
    private static String STARTDATE="startDate=";
    private static String ENDDATE="&endDate=";
    private static String TS="&ts=";
    public Pipes execute(Date startDate,Date endDate) throws IOException {
        propertiesController = new PropertiesController();
        System.out.println(this.getEndPoint(startDate, endDate));
        System.out.println(this.getBody());

        restclient = new RestClientPost(this.getEndPoint(startDate,endDate),this.getBody());
        String output = restclient.execute();
        Pipes pipes = new Pipes();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            pipes = mapper.readValue(output, Pipes.class);
        }
        return pipes;
    }
    public Map<String,Object>  getResults(Date startDate,Date endDate) throws IOException {
        Pipes pipes=execute(startDate, endDate);
        Map<String,Integer> results=new HashMap<>();
        Integer tagsFound=0;
        Integer actual=0;
        Integer markers=0;
        for (Pipe pipe:pipes.getResults()){
            if(results.containsKey(pipe.getZone())){
                results.put(pipe.getZone(),results.get(pipe.getZone())+1);
            }else{
                results.put(pipe.getZone(),1);
            }
            if(pipe.getLastInventory()!=null && pipe.getLastInventory().equals(startDate.toString())) {
                tagsFound++;
                if (pipe.getSerial().equals(pipe.getRackMarker()))
                    markers++;
            }
            actual++;
        }

        Map<String,Object> newThing=new HashMap<>();
        Integer racksFound=results.size();

        Date time=new Date();
        newThing.put("group",">Ho>Ex");
        newThing.put("name",getDate(startDate));
        newThing.put("serialNumber","INV"+getDate(startDate));//params INVDate
        newThing.put("thingTypeCode","Inventory");//para prope
        Map<String,Object> udfs=new HashMap<>();
        udfs.put("Actual",getUdfs((String.valueOf(actual))));
        udfs.put("Diff",getUdfs((String.valueOf(actual-tagsFound))));
        udfs.put("LastUpdate",getUdfs((String.valueOf(time.getTime()))));
        udfs.put("MarkersFound", getUdfs((String.valueOf(markers))));
        udfs.put("RackCount", getUdfs((String.valueOf(racksFound))));
        udfs.put("TagsFound", getUdfs((String.valueOf(tagsFound))));
        newThing.put("udfs", udfs);
        return newThing;
    }
    public String getEndPoint(Date start,Date end){
        Date currentDate=new Date();
        String params=STARTDATE+start.getTime()+ENDDATE+end.getTime()+TS+currentDate.getTime();
        return propertiesController.getEndPointPipe()+params;
    }
    public String getBody(){
        return propertiesController.getBodyPipe();
    }
    public Map<String,String> getUdfs(String value){
        Map<String,String> map=new HashMap<>();
        map.put("value",value);
        return map;
    }
    private String getDate(Date date){
        System.out.println(date.getYear());
        return String.valueOf(date.getMonth())+String.valueOf(date.getDay())+String.valueOf(date.getYear());
    }
}
