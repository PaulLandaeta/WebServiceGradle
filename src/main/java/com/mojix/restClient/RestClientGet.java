package com.mojix.restClient;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import static com.mojix.utils.VariablesUtils.*;

public class RestClientGet {

    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private HttpGet getRequest;
    private String endPoint;
    private String body;

    public RestClientGet(String endPoint,String body) throws UnsupportedEncodingException {
        httpClient = new DefaultHttpClient();
        getRequest = new HttpGet(endPoint);
        this.endPoint=endPoint;
        this.body=body;
        getRequest.addHeader(HEADER_KEY, HEADER_VALUE);
        //StringEntity input = new StringEntity("{\"\":\"\"}");
        StringEntity input = new StringEntity(body);
        input.setContentType(CONTENT_TYPE);
    }

    public String execute(){
        String output="";

        HttpResponse response = null;
        try {
            response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output+"\n");
                return output;
            }
            httpClient.getConnectionManager().shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  output;
    }



}
