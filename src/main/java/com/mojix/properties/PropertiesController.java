package com.mojix.properties;


/**
 * Created by Paul Landaeta on 25/11/2015.
 */
public class PropertiesController {
    private static final String END_POINT="endpoint.";
    private static final String PARAMS="params.";
    private static final String BODY="body.";
    private static final String BASE_URL="baseUrl";
    private static final String GROUP="group";
    private LoadProperties loadProperties;

    public PropertiesController(){
        loadProperties = LoadProperties.getInstance();
    }

    public String getEndPointUsers(){
        return loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"user");
    }

    public String getBodyUsers(){
        return "{}";
    }
    public String getParams() {
        return loadProperties.getProperty(PARAMS+"user");
    }
    public String getEndPointThings(int id){
        return loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"thing")+id;
    }
    public String getEndPointThings(){
        return loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"thing");
        //"http://exxon.mojix.com:8080/riot-core-services/api/thing/"+id;
    }

    public String getBodyThings(){
        return "{}";
    }
    public String getParamsThings() {
        return loadProperties.getProperty(PARAMS+"thing");
        //"?extra=parent,group,group.groupType,thingType,thingType.children,thingType&ts=1448";
    }
    public String getParamsThings(String serial) {
        return loadProperties.getProperty(PARAMS+"thingSerial")+serial;
        //"?extra=parent,group,group.groupType,thingType,thingType.children,thingType&ts=1448";
    }

    public String getEndPointZone(int id){
        return  loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"zone")+id;
                //"http://exxon.mojix.com:8080/riot-core-services/api/zone/"+id;
    }
    public String getBodyZones(){
        return "{}";
    }
    public String getParamsZones() {
        return loadProperties.getProperty(PARAMS+"zone");
                //"?extra=group,zoneGroup,localMap,zoneType,group.groupType&pageSize=-1&ts=1449070935205";
    }
    public String putEndPointThing(){

        return loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"thing");
                //"http://exxon.mojix.com:8080/riot-core-services/api/thing/";
    }
    public String getGroup(){
        return loadProperties.getProperty(GROUP);
    }

    public String updateparamsThing(){

        return loadProperties.getProperty(PARAMS+"thingUpdate");
        //"http://exxon.mojix.com:8080/riot-core-services/api/thing/";
    }


    public String getEndPointRacks(){

        return loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"racks");
    }

    public String getBodyRacks(){

        return loadProperties.getProperty(BODY+"racks");
    }
    public String getEndPointPipe(){

        return loadProperties.getProperty(BASE_URL)+loadProperties.getProperty(END_POINT+"pipesperRacks");
    }

    public String getBodyPipe(){

        return loadProperties.getProperty(BODY+"pipesperRacks");
    }

}