package com.mojix.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by Paul Landaeta on 11/12/2015.
 */
@ApiModel(description = "Rack Model")
public class RacksModel {
    private String startDate;
    private String endDate;

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
