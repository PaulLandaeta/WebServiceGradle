package com.mojix.model.Pipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Paul Landaeta on 11/12/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pipe {

    @JsonProperty("thingTypeId")
    private Integer thingTypeId;

    @JsonProperty("serial")
    private String serial;

    @JsonProperty("zone")
    private String zone;

    @JsonProperty("lastEvent")
    private String lastEvent;

    @JsonProperty("lastInventory")
    private String lastInventory;

    @JsonProperty("lastEventTime")
    private String lastEventTime;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("rackMarker")
    private String rackMarker;

    public String getRackMarker() {
        return rackMarker;
    }

    public void setRackMarker(String rackMarker) {
        this.rackMarker = rackMarker;
    }

    public Integer getThingTypeId() {
        return thingTypeId;
    }

    public void setThingTypeId(Integer thingTypeId) {
        this.thingTypeId = thingTypeId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(String lastEvent) {
        this.lastEvent = lastEvent;
    }

    public String getLastInventory() {
        return lastInventory;
    }

    public void setLastInventory(String lastInventory) {
        this.lastInventory = lastInventory;
    }

    public String getLastEventTime() {
        return lastEventTime;
    }

    public void setLastEventTime(String lastEventTime) {
        this.lastEventTime = lastEventTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
