package com.mojix.job;

import com.google.gson.Gson;
import com.mojix.facade.ConsumServices;
import com.mojix.restClient.RestClientPut;
import com.mojix.properties.PropertiesController;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by Paul Landaeta on 07/12/2015.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class TagsJob implements Job{

    public static final String DATE_CURRENT = "date current";
    public static final String GROUP = "group";
    public static final String DATE = "date";

    private static Logger _log = LoggerFactory.getLogger(TagsJob.class);

    public TagsJob() {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();

        JobDataMap data = context.getJobDetail().getJobDataMap();

        String date_current = data.getString(DATE_CURRENT);
        String group = data.getString(GROUP);
        String date = data.getString(DATE);

        ConsumServices consumServices=new ConsumServices();

        Gson gson = new Gson();

        String json = gson.toJson(consumServices.getTagsFound(date_current,group,date));

        PropertiesController propertiesController=new PropertiesController();

        try {
            RestClientPut restClientPut = new RestClientPut(propertiesController.putEndPointThing(),json);
            restClientPut.execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}