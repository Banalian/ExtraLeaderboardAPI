package com.extraleaderboard.logic;

import com.extraleaderboard.model.nadeo.NadeoLiveServices;
import com.extraleaderboard.model.nadeo.NadeoToken;
import com.extraleaderboard.model.ubisoft.UbisoftTicket;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.Base64;

/**
 * Class to test the token authentication flow on Nadeo's servers.
 */
public class TokenTestAuthFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenTestAuthFlow.class);

    private static final String UbisoftEmail = System.getenv("UBISOFT_EMAIL");
    private static final String UbisoftPassword = System.getenv("UBISOFT_PASSWORD");

    public NadeoToken getNewToken() {
        // First step : get Ubisoft ticket
        String ubisoftTicket = getUbisoftTicket().getTicket();

        // Second step : get Nadeo token
        return getNadeoToken(ubisoftTicket, NadeoLiveServices.getAudienceName());
    }



    private UbisoftTicket getUbisoftTicket() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://public-ubiservices.ubi.com/v3/profiles/sessions");

        return target.request()
                .header("Content-Type", "application/json")
                .header("Ubi-AppId", "86263886-327a-4328-ac69-527f0d20a237")
                .header("User-Agent", "Banalian API Test : Banalian#0584 on Discord")
                .header("Authorization", "Basic " + getBase64EncodedCredentials())
                .get(UbisoftTicket.class);
    }

    private NadeoToken getNadeoToken(final String ubisoftTicket, final String Audience) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://prod.trackmania.core.nadeo.online/v2/authentication/token/ubiservices");

        // create json body containing the audience
        JsonNode audience = new ObjectMapper().createObjectNode().put("audience", Audience);

        return target.request()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Banalian API Test : Banalian#0584 on Discord")
                .header("Authorization", "ubi_v1 t=" + ubisoftTicket)
                .post(Entity.json(audience), NadeoToken.class);
    }

    private String getBase64EncodedCredentials() {
        return Base64.getEncoder().encodeToString((UbisoftEmail + ":" + UbisoftPassword).getBytes());
    }

    public static void main(String[] args) {
        TokenTestAuthFlow tokenTestAuthFlow = new TokenTestAuthFlow();
        NadeoToken token = tokenTestAuthFlow.getNewToken();
        LOGGER.info("NadeoToken: {}", token);
    }
}
