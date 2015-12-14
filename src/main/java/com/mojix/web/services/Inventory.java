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
import com.mojix.web.models.DateInventory;
import com.mojix.web.models.RacksModel;
import com.mojix.web.utilities.RestUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Path("/Inventory")
@Api("/Inventory")
public class Inventory {
    @POST
    @Path("/byDate")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Inventory")
    public Response testweb(DateInventory currentDate) throws IOException {
        PropertiesController propertiesController = new PropertiesController();
        ThingsService thingsService=new ThingsService();
        Things things=thingsService.execute(currentDate.Name());
        ConsumServices consumServices=new ConsumServices();
        Gson gson = new Gson();
        Map<String,Object> results=consumServices.getTagsFound(currentDate.Name(), currentDate.getGroup(), currentDate.getDate());
        String json; //= gson.toJson();
        if(things.getTotal()>0){
            int id=things.getResults().get(0).getId();
            Map<String,Object> resultUpdate = new HashMap<>();
            resultUpdate.put("group",results.get("group"));
            resultUpdate.put("name",results.get("name"));
            resultUpdate.put("serialNumber",results.get("serialNumber"));//params INVDate
            resultUpdate.put("thingTypeCode","Inventory");//para prope
            resultUpdate.put("udfs",results.get("udfs"));
            String url=propertiesController.getEndPointThings()+id+propertiesController.updateparamsThing();
            json=gson.toJson(resultUpdate);
            RestClientPatch restClientPatch=new RestClientPatch(url,json);
            restClientPatch.execute();
            return RestUtils.sendOkResponse("The thing has updated");
        }
        else{
            try{
                json=gson.toJson(results);
                RestClientPut restClientPut=new RestClientPut(propertiesController.putEndPointThing(),json);
                restClientPut.execute();
                return RestUtils.sendOkResponse("The thing has created");
            }
            catch (Exception e) {
                return RestUtils.sendBadResponse(e.toString());
            }
        }
    }
    @POST
    @Path("/Racks")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Racks")
    public Response getRacks(RacksModel racksModel) throws IOException {
        Date startDate=new Date(racksModel.getStartDate());
        Date endDate=new Date(racksModel.getEndDate());
        RackService rackService=new RackService();
        Racks racks=rackService.execute(startDate, endDate);
        return RestUtils.sendOkResponse(racks.getSeries());
    }
    @POST
    @Path("/Pipes")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Pipes")
    public Response getPipes(RacksModel racksModel) throws IOException {
        PropertiesController propertiesController = new PropertiesController();

        Date startDate=new Date(racksModel.getStartDate());
        Date endDate=new Date(racksModel.getEndDate());

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
                System.out.println("asdasdasdasdasds");
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


      //  return RestUtils.sendOkResponse(newThing);
    }

}