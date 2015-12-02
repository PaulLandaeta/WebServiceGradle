package com.mojix.model.thing;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Paul Landaeta on 30/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Things {
    private int total;

    private List<Thing> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Thing> getResults() {
        return results;
    }

    public void setResults(List<Thing> results) {
        this.results = results;
    }
}
