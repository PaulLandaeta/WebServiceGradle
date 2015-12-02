package com.mojix.model.zone;

import java.util.List;

/**
 * Created by Paul Landaeta on 02/12/2015.
 */
public class ZoneType {

    private Object zoneTypeCode;

    private String name;

    private String  description;

    private String id;

    private List<ZoneProperties> zoneProperties;

    public Object getZoneTypeCode() {
        return zoneTypeCode;
    }

    public void setZoneTypeCode(Object zoneTypeCode) {
        this.zoneTypeCode = zoneTypeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ZoneProperties> getZoneProperties() {
        return zoneProperties;
    }

    public void setZoneProperties(List<ZoneProperties> zoneProperties) {
        this.zoneProperties = zoneProperties;
    }
}
