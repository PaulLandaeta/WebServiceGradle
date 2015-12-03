package com.mojix.model.thing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mojix.model.Tag.Fields;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by Paul Landaeta on 30/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thing {
    @JsonProperty("modifiedTime")
    private String modifiedTime;

    @JsonProperty("parent")
    private Object parent;

    @JsonProperty("group.groupType")
    private List<Map<Object,Object>> groupType;

    private int treeLevel;
    private String serial;
    private List<Map<Object,Object>> children;
    private String name;
    private int id;

    @JsonProperty("thingType")
    private Object thingType;
    @JsonProperty("fields")
    private List<Fields> fields;

    private boolean selected;
    private boolean activated;
    @JsonProperty("group")
    private Map<Object,Object> group;

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }
    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Map<Object, Object> getGroup() {
        return group;
    }

    public void setGroup(Map<Object, Object> group) {
        this.group = group;
    }

    public int getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(int treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public List<Map<Object, Object>> getChildren() {
        return children;
    }

    public void setChildren(List<Map<Object, Object>> children) {
        this.children = children;
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

    public Object getThingType() {
        return thingType;
    }

    public void setThingType(Object thingType) {
        this.thingType = thingType;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public List<Map<Object, Object>> getGroupType() {
        return groupType;
    }

    public void setGroupType(List<Map<Object, Object>> groupType) {
        this.groupType = groupType;
    }
}
