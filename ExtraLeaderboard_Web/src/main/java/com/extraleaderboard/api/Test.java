package com.extraleaderboard.api;

import com.extraleaderboard.business.TestBusiness;
import com.extraleaderboard.model.nadeo.NadeoToken;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("/test")
public class Test {

    @EJB
    TestBusiness testBusiness;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/token")
    @Produces("application/json")
    public NadeoToken getToken() {
        return testBusiness.getToken();
    }
}