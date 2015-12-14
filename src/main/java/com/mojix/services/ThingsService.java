package com.mojix.services;

import com.mojix.model.Tag.Tag;
import com.mojix.model.thing.Things;
import com.mojix.properties.PropertiesController;
import com.mojix.restClient.RestClientGet;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Paul Landaeta on 30/11/2015.
 */
public class ThingsService {
    private RestClientGet restclient;
    private PropertiesController propertiesController;

    public Things execute(String serialName) throws IOException {
        propertiesController = new PropertiesController();
        restclient = new RestClientGet(propertiesController.getEndPointThings()+propertiesController.getParamsThings(serialName),propertiesController.getBodyUsers());
        String output = restclient.execute();
        Things things=new Things();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            things = mapper.readValue(output, Things.class);
        }
        return things;
    }


}
