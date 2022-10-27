package com.extraleaderboard.logic;

import com.extraleaderboard.logic.exception.NadeoAPIResponseException;
import com.extraleaderboard.logic.token.TokenFactory;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Class used to call the Nadeo API. Allow to set an audience, and request an endpoint with parameters. The token is handled by the class.
 */
public class NadeoHttpRequest {

    /**
     * Token to use for the request
     */
    private final NadeoToken token;

    /**
     * url of the endpoint to call
     */
    private String url;

    /**
     * Parameters to send with the request
     */
    private Map<String, String> parameters;

    /**
     * Constructor
     *
     * @param audience audience to use for the request
     */
    public NadeoHttpRequest(Audience audience) {
        this.token = TokenFactory.getToken(audience);
    }

    public NadeoHttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public NadeoHttpRequest setParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public NadeoHttpRequest removeParameter(String key) {
        this.parameters.remove(key);
        return this;
    }

    public String getParameter(String key) {
        return this.parameters.get(key);
    }

    public NadeoHttpRequest clearParameters() {
        this.parameters.clear();
        return this;
    }

    public Object execute() throws NadeoAPIResponseException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(this.url)
                .queryParam("Authorization", "nadeo_v1 t=" + this.token.getAccessToken());
        for (Map.Entry<String, String> entry : this.parameters.entrySet()) {
            target = target.queryParam(entry.getKey(), entry.getValue());
        }

        Response response = target.request().get();

        // check the response status code before returning the response
        // If the response is in the 200 range, return the response
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return response.readEntity(ObjectNode.class);
        } else {
            String message = "Error while calling the Nadeo API. Status code: " + response.getStatus()
                    + ", message: " + response.getStatusInfo().getReasonPhrase();
            throw new NadeoAPIResponseException(message);
        }
    }


}
