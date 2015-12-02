package com.mojix.services;

import com.mojix.properties.LoadProperties;

/**
 * Created by Paul Landaeta on 25/11/2015.
 */
public class PropertiesController {
    private static final String END_POINT="endpoint.";
    private static final String BODY="body.";
    private LoadProperties loadProperties;

    public PropertiesController(){
        loadProperties = LoadProperties.getInstance();
    }

    public String getEndPointUsers(){
        return "http://exxon.mojix.com:8080/riot-core-services/api/thing/tree";
    }

    public String getBodyUsers(){
        return "{}";
    }
    public String getParams() {
        return "?extra=parent,group,fields,group.groupType,thingType&order=name:asc&pageNumber=1&pageSize=15&ts=1448894218388&upVisibility=false&where=thingType.id%3D1";
    }
    public String getEndPointThings(int id){
        return "http://exxon.mojix.com:8080/riot-core-services/api/thing/"+id;
    }

    public String getBodyThings(){
        return "{}";
    }
    public String getParamsThings() {
        return "?extra=parent,group,group.groupType,thingType,thingType.children,thingType&ts=1448";
    }

    public String getEndPointZone(int id){
        return "http://exxon.mojix.com:8080/riot-core-services/api/zone/"+id;
    }
    public String getBodyZones(){
        return "{}";
    }
    public String getParamsZones() {
        return "?extra=group,zoneGroup,localMap,zoneType,group.groupType&pageSize=-1&ts=1449070935205";
    }
}