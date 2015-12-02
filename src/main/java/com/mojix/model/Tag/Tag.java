package com.mojix.model.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by Paul Landaeta on 30/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

    private String modifiedTime;

    private Object parent;
    @JsonProperty("group.groupType")
    private Map<Object,Object> groupType;
    @JsonProperty("thingType.children")
    private List<Map<Object,Object>> ThingTypeChildren;
    private List<Object> childrenFields;
    private List<Object> parentFields;
    private String serial;
    private String name;
    private String id;
    private Map<Object,Object> thingType;
    private List<Fields> fields;
    private boolean activated;
    private Object group;

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public Object getGroupType() {
        return groupType;
    }

    public void setGroupType(Map<Object, Object> groupType) {
        this.groupType = groupType;
    }

    public List<Map<Object, Object>> getThingTypeChildren() {
        return ThingTypeChildren;
    }

    public void setThingTypeChildren(List<Map<Object, Object>> thingTypeChildren) {
        ThingTypeChildren = thingTypeChildren;
    }

    public List<Object> getChildrenFields() {
        return childrenFields;
    }

    public void setChildrenFields(List<Object> childrenFields) {
        this.childrenFields = childrenFields;
    }

    public List<Object> getParentFields() {
        return parentFields;
    }

    public void setParentFields(List<Object> parentFields) {
        this.parentFields = parentFields;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Object, Object> getThingType() {
        return thingType;
    }

    public void setThingType(Map<Object, Object> thingType) {
        this.thingType = thingType;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }
}
