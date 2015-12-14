package com.mojix.facade;

import com.mojix.services.Tags;

import java.io.IOException;
import java.util.Map;

public class ConsumServices  {


    private static ConsumServices instance=new ConsumServices();
    private Map<String,Object> map;

    public ConsumServices(){

    }
    public static ConsumServices getInstance(){
        return instance;
    }

    public Map<String,Object> getTagsFound(String dateCurrent,String group,String date){
        Map<String,Object> tagList;
        try {
            Tags thingsService=new Tags();
            map = thingsService.execute(dateCurrent,group,date);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error","ConsumServices Error:"+e.getMessage());
        }
        return map;
    }

}
