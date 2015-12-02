package com.mojix.model.tag;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by Paul Landaeta on 30/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
   private Object symbol;

   private String defaultValue;

   private String thingTypeFieldTemplateId;

   private Map<Object,Object> dataType;

   private String dataTypeThingTypeId;

   private boolean multiple;

   private String typeParent;

   private int type;

   private int thingId;

   private String timeToLive;

   private boolean timeSeries;

   private String unit;

   private String name;

   private int id;

   private Map<Object,Object> thingTypeField;

   private Object value;

    public Object getSymbol() {
        return symbol;
    }

    public void setSymbol(Object symbol) {
        this.symbol = symbol;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getThingTypeFieldTemplateId() {
        return thingTypeFieldTemplateId;
    }

    public void setThingTypeFieldTemplateId(String thingTypeFieldTemplateId) {
        this.thingTypeFieldTemplateId = thingTypeFieldTemplateId;
    }

    public Map<Object, Object> getDataType() {
        return dataType;
    }

    public void setDataType(Map<Object, Object> dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeThingTypeId() {
        return dataTypeThingTypeId;
    }

    public void setDataTypeThingTypeId(String dataTypeThingTypeId) {
        this.dataTypeThingTypeId = dataTypeThingTypeId;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getTypeParent() {
        return typeParent;
    }

    public void setTypeParent(String typeParent) {
        this.typeParent = typeParent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getThingId() {
        return thingId;
    }

    public void setThingId(int thingId) {
        this.thingId = thingId;
    }

    public String getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(String timeToLive) {
        this.timeToLive = timeToLive;
    }

    public boolean isTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(boolean timeSeries) {
        this.timeSeries = timeSeries;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Object, Object> getThingTypeField() {
        return thingTypeField;
    }

    public void setThingTypeField(Map<Object, Object> thingTypeField) {
        this.thingTypeField = thingTypeField;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
