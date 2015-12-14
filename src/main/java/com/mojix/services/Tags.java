package com.mojix.services;


import com.mojix.model.Tag.Fields;
import com.mojix.model.rack.Rack;
import com.mojix.model.rack.Racks;
import com.mojix.model.thing.Thing;
import com.mojix.model.thing.Things;
import com.mojix.model.zone.Zone;
import com.mojix.model.zone.ZoneProperties;
import com.mojix.properties.PropertiesController;
import com.mojix.restClient.RestClientGet;
import com.mojix.web.utilities.RestUtils;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Paul Landaeta on 30/11/2015.
 */
public class Tags {
    private static String LAST_INVENTORY="lastInventory";
    private static String ZONE="zone";
    private static String CODE="code";
    private static String ID="id";
    private RestClientGet restclient;
    private PropertiesController propertiesController;

    public Map<String,Object> execute(String nameDate,String group,String date) throws IOException {
        propertiesController = new PropertiesController();

        //Obtain Racks for Day.
        Racks dayRacks=this.getRack(date);

        //Obtain all things.
        Things things=this.getThings();



        Map<String,List<Thing >> zones=new HashMap<>();

        Map<String,Integer> zonesCurrent=new HashMap<>();

        for (Rack rack:dayRacks.getSeries()){
            zonesCurrent.put(rack.getName(), (Integer) rack.getData().get(0));
        }



        for(Thing thing:things.getResults()){
            if(thing.getFields()!=null) {
                String lastInventory="";
                String codeZone="";
                for (Fields fields : thing.getFields()) {
                    if(fields!=null && fields.getName()!=null && fields.getValue()!=null) {

                        if (fields.getName().equals(LAST_INVENTORY) ){
                            lastInventory= (String) fields.getValue();
                        }
                        if(fields.getName().equals(ZONE)){
                            codeZone=this.getID(String.valueOf(fields.getValue()));

                        }
                    }
                }
                if(lastInventory.equals(date) && !codeZone.equals("")) {
                    if(zones.containsKey(codeZone)){
                        List<Thing> thingsZone = zones.get(codeZone);
                        thingsZone.add(thing);
                    }
                    else{
                        List<Thing> thingsZone = new ArrayList<>();
                        thingsZone.add(thing);
                        zones.put(codeZone,thingsZone);
                    }
                }
            }
        }

        int markerfound=0;
        int tagsFound=0;
        int current=0;

        Iterator zoneIt = zones.entrySet().iterator();
        while (zoneIt.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) zoneIt.next();
            List<Thing> value = (List<Thing>) thisEntry.getValue();
            Zone zone;
            System.out.println(thisEntry.getKey());
            tagsFound+=value.size();
            ZoneService zoneService=new ZoneService();
            if(!thisEntry.getKey().equals("")){
                zone = zoneService.execute((Integer.valueOf((String) thisEntry.getKey())));
                current+=zonesCurrent.get(zone.getCode());
                for(ZoneProperties zoneProperties:zone.getZoneType().getZoneProperties()){
                     if(zoneProperties.getName().equals("marker") && !zoneProperties.getValue().equals("")) {
                            for (Thing thing:value){
                                if(thing.getSerial().equals(zoneProperties.getValue()))
                                    markerfound++;
                            }
                     }
                }
            }
        }

        Map<String,Object> newThing=new HashMap<>();

        Date time=new Date();
        newThing.put("group",group);
        newThing.put("name",nameDate);
        newThing.put("serialNumber",nameDate);//params INVDate
        newThing.put("thingTypeCode","Inventory");//para prope
        Map<String,Object> udfs=new HashMap<>();
        udfs.put("Actual",getUdfs((String.valueOf(current))));
        udfs.put("Diff",getUdfs((String.valueOf(current-tagsFound))));
        udfs.put("LastUpdate",getUdfs((String.valueOf(time.getTime()))));
        udfs.put("MarkersFound",getUdfs((String.valueOf(markerfound))));
        udfs.put("RackCount",getUdfs((String.valueOf(zones.size()))));
        udfs.put("TagsFound",getUdfs((String.valueOf(tagsFound))));
        newThing.put("udfs",udfs);

        int diff=current-tagsFound;
        Date lastUpdate=new Date();
        System.out.println("Nros Current"+current);
        System.out.println("Nros Tags"+tagsFound);
        System.out.println("Nros rack"+zones.size());
        System.out.println("Nro Markers"+markerfound);
        System.out.println("Nro Diff"+diff);
        return newThing;
    }
    public String getID(String value){
        String id="";
        if(value.contains(ID)){
            int indexOfCode=value.indexOf(ID);
            if(indexOfCode>-1){
                for(int i=indexOfCode+3;i<value.length();i++)
                    if(value.charAt(i)==',' || value.charAt(i)=='}')
                        break;
                    else
                        id+=value.charAt(i);
            }
        }
        return id;
    }
    public Map<String,String> getUdfs(String value){
        Map<String,String> map=new HashMap<>();
        map.put("value",value);
        return map;
    }
    private Racks getRack(String sDate) throws IOException {
        Date startDate=new Date(sDate);
        Date endDate= DateUtils.addDays(startDate, 1);
        RackService rackService=new RackService();
        Racks racks=rackService.execute(startDate, endDate);
        return racks;
    }

    private Things getThings() throws IOException {
        propertiesController = new PropertiesController();
        String endPoint=propertiesController.getEndPointUsers()+propertiesController.getParams();
        String body=propertiesController.getBodyUsers();
        restclient = new RestClientGet(endPoint,body);
        String output = restclient.execute();
        Things things=new Things();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            things = mapper.readValue(output, Things.class);

        }
        return things;
    }
}
