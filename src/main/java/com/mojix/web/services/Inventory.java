package com.mojix.web.services;


import com.google.gson.Gson;
import com.mojix.facade.ConsumServices;
import com.mojix.model.Pipe.Pipe;
import com.mojix.model.Pipe.Pipes;
import com.mojix.model.rack.Racks;
import com.mojix.model.thing.Things;
import com.mojix.properties.PropertiesController;
import com.mojix.restClient.RestClientPatch;
import com.mojix.restClient.RestClientPut;
import com.mojix.services.PipesService;
import com.mojix.services.RackService;
import com.mojix.services.ThingsService;
import com.mojix.utils.DateUtil;
import com.mojix.web.models.DateInventory;
import com.mojix.web.models.RacksModel;
import com.mojix.web.utilities.RestUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateUtils;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Path("/Inventory")
@Api("/Inventory")
public class Inventory {
    @POST
    @Path("/Pipes")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(position = 0, value="Pipes")
    public Response getPipes(RacksModel racksModel) throws IOException, ParseException {
        PropertiesController propertiesController = new PropertiesController();
        DateUtil dateUtil=new DateUtil(racksModel.getStartDate());
        if(dateUtil.isValidDate()){
            Date startDate=dateUtil.getStartDate();
            Date endDate= DateUtils.addDays(startDate,1);

            PipesService pipesService=new PipesService();
            Map<String,Object> newThing=pipesService.getResults(startDate,endDate);

            ThingsService thingsService=new ThingsService();
            Things things=thingsService.execute("INV"+racksModel.getStartDate());

            Gson gson = new Gson();

            String json;
            if(things.getTotal()>0){
                int id=things.getResults().get(0).getId();
                Map<String,Object> resultUpdate = new HashMap<>();
                resultUpdate.put("group",newThing.get("group"));
                resultUpdate.put("name",newThing.get("name"));
                resultUpdate.put("serialNumber",newThing.get("serialNumber"));
                resultUpdate.put("thingTypeCode","Inventory");
                resultUpdate.put("udfs",newThing.get("udfs"));
                String url=propertiesController.getEndPointThings()+id+propertiesController.updateparamsThing();
                json=gson.toJson(resultUpdate);
                RestClientPatch restClientPatch=new RestClientPatch(url,json);
                restClientPatch.execute();
                return RestUtils.sendOkResponse("The thing has updated");
            }
            else{
                try{
                    json=gson.toJson(newThing);
                    System.out.println(json);
                    RestClientPut restClientPut=new RestClientPut(propertiesController.putEndPointThing(),json);
                    restClientPut.execute();
                    return RestUtils.sendOkResponse("The thing has created");
                }
                catch (Exception e) {
                    return RestUtils.sendBadResponse(e.toString());
                }
            }

        }
        else{
            return RestUtils.sendBadResponse("Invalid Date");
        }
    }

}