package com.mojix.model.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Paul Landaeta on 01/12/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

   public class Zone {
    @JsonProperty("group.groupType")
    private Object groupType;

    private String code;

    private String color;

    private Object zoneGroup;

    private String name;

    private String description;

    private ZoneType zoneType;

    private Object localMap;

    private String id;

    private Object zonePoints;

    private Object group;

    public Object getGroupType() {
        return groupType;
    }

    public void setGroupType(Object groupType) {
        this.groupType = groupType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getZoneGroup() {
        return zoneGroup;
    }

    public void setZoneGroup(Object zoneGroup) {
        this.zoneGroup = zoneGroup;
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

    public ZoneType getZoneType() {
        return zoneType;
    }

    public void setZoneType(ZoneType zoneType) {
        this.zoneType = zoneType;
    }

    public Object getLocalMap() {
        return localMap;
    }

    public void setLocalMap(Object localMap) {
        this.localMap = localMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getZonePoints() {
        return zonePoints;
    }

    public void setZonePoints(Object zonePoints) {
        this.zonePoints = zonePoints;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }
}
