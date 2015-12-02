package com.mojix.web.services;


import com.mojix.web.utilities.RestUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/auth")
@Api("/auth")
public class Test {
    @GET
    @Path("/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Test")
    public Response testweb() {
        return RestUtils.sendOkResponse("Good");
    }
}