package com.mojix.model.rack;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Paul Landaeta on 11/12/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Racks {

    private Object  thingFieldTypeY;

    private Object startDate;

    private Object endDate;

    private String labelY;

    private String title;

    private List<Rack> series;

    public Object getThingFieldTypeY() {
        return thingFieldTypeY;
    }

    public void setThingFieldTypeY(Object thingFieldTypeY) {
        this.thingFieldTypeY = thingFieldTypeY;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getLabelY() {
        return labelY;
    }

    public void setLabelY(String labelY) {
        this.labelY = labelY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Rack> getSeries() {
        return series;
    }

    public void setSeries(List<Rack> series) {
        this.series = series;
    }
}
