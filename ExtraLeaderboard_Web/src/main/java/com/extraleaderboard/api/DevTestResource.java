package com.extraleaderboard.api;

import com.extraleaderboard.business.TestBusiness;
import com.extraleaderboard.model.nadeo.Audience;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/devtest")
public class DevTestResource {

    @EJB
    private TestBusiness testBusiness;

    public DevTestResource() {
    }

    /**
     * Test method to call the Nadeo API to get a token. Produces text
     *
     * @param secret   secret to use for the token
     * @param audience audience to use for the token
     * @return the token
     */
    @Path("/token")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test(@QueryParam("secret") String secret, @QueryParam("audience") Integer audience) {
        String propSecret = System.getProperty("api.devtest.secret");
        if (secret.equalsIgnoreCase(propSecret)) {
            return switch (audience) {
                case 0 -> testBusiness.getToken(Audience.NADEO_LIVE_SERVICES).getAccessToken();
                case 1 -> testBusiness.getToken(Audience.NADEO_CLUB_SERVICES).getAccessToken();
                case 2 -> testBusiness.getToken(Audience.NADEO_SERVICES).getAccessToken();
                default -> "Invalid audience";
            };
        } else {
            return "Invalid secret";
        }
    }
}
