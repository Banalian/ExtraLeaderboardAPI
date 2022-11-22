package com.extraleaderboard.model;

import com.extraleaderboard.model.nadeoresponse.NadeoResponse;
import com.extraleaderboard.model.nadeo.Audience;

import java.util.Collections;
import java.util.Map;

/**
 * Object corresponding to a request the leaderboards API send to the Nadeo API.
 *
 * @author Adam NASSIRI
 */
public class Request implements Cloneable {

    /**
     * Main request constructor
     *
     * @param audience         the audience que want to request to
     * @param newUrl           the targeted url (including url params)
     * @param newQueryParamMap map of all query parameters
     * @param newResponseType  enum specifying the expected response type
     */
    Request(Audience audience, String newUrl, Map<String, String> newQueryParamMap, ResponseType newResponseType) {
        this.audience = audience;
        this.endPoint = newUrl;
        this.queryParamMap = newQueryParamMap;
        this.responseType = newResponseType;
    }

    /**
     * Represents the expected response type corresponding to the current Request
     */
    public enum ResponseType {
        TIME, POSITION, PERCENTAGE,
    }

    private String endPoint;

    private Audience audience;


    private Map<String, String> queryParamMap;


    private ResponseType responseType;

    private NadeoResponse response;

    /**
     *
     * @return the exected response type of the current Request
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     *
     * @return the query parameters of the current Request
     */
    public Map<String, String> getQueryParamMap() {
        return Collections.unmodifiableMap(queryParamMap);
    }

    /**
     *
     * @return the audience of the current Request
     */
    public Audience getAudience() {
        return audience;
    }

    /**
     * @param newUrl
     */
    public void setEndPoint(String newUrl) {
        this.endPoint = newUrl;
    }

    /**
     *
     * @return the endpoint of the current Request
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     *
     * @return the resonse of the current Request
     */
    public NadeoResponse getResponse() {
        return this.response;
    }

    /**
     *
     * @param newResponse
     */
    public void setResponse(NadeoResponse newResponse) {
        this.response = newResponse;
    }


    /**
     * Request cloning method
     *
     * @return a clone of the current Request
     */
    @Override
    public Request clone() {
        try {
            Request clone = (Request) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
