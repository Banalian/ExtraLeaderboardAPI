package com.extraleaderboard.logic;

import com.extraleaderboard.logic.exception.NadeoAPIResponseException;
import com.extraleaderboard.logic.token.TokenFactory;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to call the Nadeo API. Allow to set an audience, and request an endpoint with parameters. The token is handled by the class.
 */
public class NadeoHttpRequest {

    /**
     * User agent used
     */
    private static final String USER_AGENT = "ExtraLeaderboard API : extraleaderboard@gmail.com";
    /**
     * private client used to make the request
     */
    private static final Client CLIENT = ClientBuilder.newClient();
    /**
     * Token to use for the request
     */
    private final NadeoToken token;
    /**
     * Parameters to send with the request
     */
    private final Map<String, Object> parameters;
    /**
     *
     */
    private Class<? extends NadeoResponse> returnClass;
    /**
     * url of the endpoint to call
     */
    private String url;

    /**
     * Constructor
     *
     * @param audience audience to use for the request
     */
    public NadeoHttpRequest(Audience audience) {
        parameters = new HashMap<>();
        this.token = TokenFactory.getToken(audience);
    }

    /**
     * Class that is expected to be returned
     */
    public Class<? extends NadeoResponse> getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(Class<? extends NadeoResponse> returnClass) {
        this.returnClass = returnClass;
    }

    /**
     * Set the url of the endpoint to call
     *
     * @param url url of the endpoint to call
     * @return the current instance
     */
    public NadeoHttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Set the parameters to send with the request
     *
     * @param key   key of the parameter
     * @param value value of the parameter
     * @return the current instance
     */
    public NadeoHttpRequest setParameter(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    /**
     * Remove a parameter from the request
     *
     * @param key key of the parameter to remove
     * @return the current instance
     */
    public NadeoHttpRequest removeParameter(String key) {
        this.parameters.remove(key);
        return this;
    }

    /**
     * Get a parameter from the request
     *
     * @param key key of the parameter to get
     * @return the value of the parameter
     */
    public Object getParameter(String key) {
        return this.parameters.get(key);
    }

    /**
     * Clear all the parameters
     *
     * @return the current instance
     */
    public NadeoHttpRequest clearParameters() {
        this.parameters.clear();
        return this;
    }

    /**
     * Call the endpoint with the given parameters
     *
     * @return the response of the request as an {@link ObjectNode}
     * @throws NadeoAPIResponseException if the response is not a SUCCESS
     */
    public NadeoResponse execute() throws NadeoAPIResponseException {
        WebTarget target = CLIENT
                .target(this.url);

        for (Map.Entry<String, Object> entry : this.parameters.entrySet()) {
            target = target.queryParam(entry.getKey(), entry.getValue());
        }

        Response response = target.request()
                .header("Authorization", "nadeo_v1 t=" + this.token.getAccessToken())
                .header("User-Agent", USER_AGENT)
                .get();

        // check the response status code before returning the response
        // If the response is in the 200 range, return the response
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return response.readEntity(returnClass);
        } else {
            String message = "Error while calling the Nadeo API. Status code: " + response.getStatus()
                    + ", message: " + response.getStatusInfo().getReasonPhrase();
            throw new NadeoAPIResponseException(message);
        }
    }


}
