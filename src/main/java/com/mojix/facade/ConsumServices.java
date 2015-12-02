package com.mojix.facade;

import com.mojix.model.thing.Thing;
import com.mojix.services.Tags;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConsumServices  {


    private static ConsumServices instance=new ConsumServices();
    private Map<String,String> map;

    public ConsumServices(){

    }
    public static ConsumServices getInstance(){
        return instance;
    }

    public Map<String,String> getTagsFound(){
        List<Thing> tagList;
        try {
            Tags thingsService=new Tags();
            tagList = thingsService.execute("24/11/2015");

        } catch (IOException e) {
            e.printStackTrace();
            map.put("error","ConsumServices Error:"+e.getMessage());
        }
        return map;
    }

}
