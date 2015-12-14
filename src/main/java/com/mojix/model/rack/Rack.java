package com.mojix.model.rack;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Paul Landaeta on 11/12/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rack {
    private List<Object> data;

    private String name;

    private String type;

    private List<Object> colors;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getColors() {
        return colors;
    }

    public void setColors(List<Object> colors) {
        this.colors = colors;
    }
}
