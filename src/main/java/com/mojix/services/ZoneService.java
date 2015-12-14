package com.mojix.services;

import com.mojix.model.zone.Zone;
import com.mojix.properties.PropertiesController;
import com.mojix.restClient.RestClientGet;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Paul Landaeta on 02/12/2015.
 */
public class ZoneService {
    private RestClientGet restclient;
    private PropertiesController propertiesController;

    public Zone execute(int id) throws IOException {
        propertiesController = new PropertiesController();
        restclient = new RestClientGet(propertiesController.getEndPointZone(id)+propertiesController.getParamsZones(),propertiesController.getBodyZones());
        String output = restclient.execute();
        Zone zone=new Zone();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            zone = mapper.readValue(output,Zone.class);
          //  System.out.println(zone.getZoneType().getZoneProperties().get(1).getValue());
        }
        return zone;
    }
}
