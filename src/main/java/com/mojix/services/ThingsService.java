package com.mojix.services;

import com.mojix.model.tag.Tag;
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

    public Map<String,String> execute(int id) throws IOException {
        propertiesController = new PropertiesController();
        restclient = new RestClientGet(propertiesController.getEndPointThings(id)+propertiesController.getParamsThings(),propertiesController.getBodyUsers());
        String output = restclient.execute();
        Tag tag=new Tag();
        if(!output.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            tag = mapper.readValue(output, Tag.class);
            System.out.println(tag.getFields().size());
        }
        return null;
    }
}
