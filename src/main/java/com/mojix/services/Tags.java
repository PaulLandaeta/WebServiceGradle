package com.mojix.services;


import com.mojix.model.Tag.Fields;
import com.mojix.model.thing.Thing;
import com.mojix.model.thing.Things;
import com.mojix.model.zone.Zone;
import com.mojix.model.zone.ZoneProperties;
import com.mojix.restClient.RestClientGet;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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

    public List<Thing> execute(String date) throws IOException {
        propertiesController = new PropertiesController();
        restclient = new RestClientGet(propertiesController.getEndPointUsers()+propertiesController.getParams(),propertiesController.getBodyUsers());
        String output = restclient.execute();
        Things things=new Things();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            things = mapper.readValue(output, Things.class);

        }
        Map<String,List<Thing > > zones=new HashMap<>();
        Map<String,List<Thing > > zonesCurrent=new HashMap<>();
        List<Thing> thingsCurrent=new ArrayList<>();
        for(Thing thing:things.getResults()){
            if(thing.getFields()!=null) {
                String lastInventory="";
                String idZone="";
                for (Fields fields : thing.getFields()) {
                    if(fields!=null && fields.getName()!=null && fields.getValue()!=null) {

                        if (fields.getName().equals(LAST_INVENTORY) ){
                            //thingsCurrent.add(thing);
                            lastInventory= (String) fields.getValue();
                        }
                        if(fields.getName().equals(ZONE)){
                            idZone=this.getId(String.valueOf(fields.getValue()));
                            if(zonesCurrent.containsKey(idZone)){
                                List<Thing> thingsZone = zonesCurrent.get(idZone);
                                thingsZone.add(thing);
                            }
                            else{
                                List<Thing> thingsZone = new ArrayList<>();
                                thingsZone.add(thing);
                                zonesCurrent.put(idZone,thingsZone);
                            }
                        }
                    }
                    if(lastInventory.equals(date)) {
                        if(zones.containsKey(idZone)){
                            List<Thing> thingsZone = zones.get(idZone);
                            thingsZone.add(thing);
                        }
                        else{
                            List<Thing> thingsZone = new ArrayList<>();
                            thingsZone.add(thing);
                            zones.put(idZone,thingsZone);
                        }
                    }
                }
            }
        }
        int markerfound=0;
        int tagsFound=0;
        int current=things.getResults().size();

        Iterator zoneIt = zones.entrySet().iterator();
        while (zoneIt.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) zoneIt.next();
            List<Thing> value = (List<Thing>) thisEntry.getValue();
            Zone zone = new Zone();
            tagsFound+=value.size();
            ZoneService zoneService=new ZoneService();
            if(!thisEntry.getKey().equals("")){
                zone = zoneService.execute((Integer.valueOf((String) thisEntry.getKey())));
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
        int diff=current-tagsFound;
        Date lastUpdate=new Date();
        System.out.println("Nros Current"+current);
        System.out.println("Nros Tags"+tagsFound);
        System.out.println("Nros rack"+zones.size());
        System.out.println("Nro Markers"+markerfound);
        System.out.println("Nro Diff"+diff);

        return thingsCurrent;
    }
    public String getId(String value){
        String id="";
        if(value.contains(ID)){
            int indexOfCode=value.indexOf(ID);
            if(indexOfCode>-1){
                for(int i=indexOfCode+5;i<value.length();i++)
                    if(value.charAt(i)==',' || value.charAt(i)=='}')
                        break;
                    else
                        id+=value.charAt(i);
            }
        }
        return id;
    }
}
