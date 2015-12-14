package com.mojix.restClient;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import static com.mojix.utils.VariablesUtils.*;

public class RestClientPut {

    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private HttpPut putRequest;
    private String endPoint;
    private String body;

    public RestClientPut(String endPoint,String body) throws UnsupportedEncodingException {
        httpClient = new DefaultHttpClient();
        putRequest = new HttpPut(endPoint);
        
        this.endPoint=endPoint;
        this.body=body;
        putRequest.addHeader(HEADER_KEY_PUT, HEADER_VALUE_PUT);
        putRequest.addHeader("Access-Control-Allow-Origin", "*");
        putRequest.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        putRequest.addHeader("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
        putRequest.addHeader("Access-Control-Allow-Credentials", "true");
        //StringEntity input = new StringEntity("{\"\":\"\"}");
        StringEntity input = new StringEntity(body);
        //Entity
        input.setContentType(CONTENT_TYPE);
        putRequest.setEntity(input);
    }

    public String execute(){
        String output="";

        HttpResponse response = null;
        try {
            response = httpClient.execute(putRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                output= String.valueOf(response.getStatusLine().getStatusCode());
                throw new RuntimeException("Failed : HTTP error code : "
                        + response);

            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            output= String.valueOf(response.getStatusLine().getStatusCode());
            /*
            while ((output = br.readLine()) != null) {
                System.out.println(output+"\n");
                return output;
            }*/
            httpClient.getConnectionManager().shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  output;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
