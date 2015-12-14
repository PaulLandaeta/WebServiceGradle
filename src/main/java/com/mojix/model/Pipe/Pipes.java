package com.mojix.model.Pipe;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Paul Landaeta on 11/12/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pipes {
    private Integer total;

    private Long endDate;

    private List<Pipe> results;

    private Object thingFieldTypeMap;

    private Long startDate;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public List<Pipe> getResults() {
        return results;
    }

    public void setResults(List<Pipe> results) {
        this.results = results;
    }

    public Object getThingFieldTypeMap() {
        return thingFieldTypeMap;
    }

    public void setThingFieldTypeMap(Object thingFieldTypeMap) {
        this.thingFieldTypeMap = thingFieldTypeMap;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }
}
