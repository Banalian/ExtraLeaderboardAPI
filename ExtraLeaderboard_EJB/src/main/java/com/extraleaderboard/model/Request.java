package com.extraleaderboard.model;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeoresponse.NadeoMapResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoPositionResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoTimeResponse;

import java.util.Collections;
import java.util.Map;

/**
 * Object corresponding to a request the leaderboards API send to the Nadeo API.
 *
 * @author Adam NASSIRI
 */
public class Request implements Cloneable {

    private String endPoint;
    private final Map<String, Object> queryParamMap;
    private final ResponseType responseType;
    private final RequestType requestType;
    private NadeoResponse response;
    private Audience audience;

    /**
     * Main request constructor
     *
     * @param newUrl           the targeted url (including url params)
     * @param newQueryParamMap map of all query parameters
     * @param newResponseType  enum specifying the expected response type
     */
    public Request(Audience audience, String newUrl, Map<String, Object> newQueryParamMap, ResponseType newResponseType, RequestType requestType) {
        this.audience = audience;
        this.endPoint = newUrl;
        this.queryParamMap = newQueryParamMap;
        this.responseType = newResponseType;
        this.requestType = requestType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Map<String, Object> getQueryParamMap() {
        return Collections.unmodifiableMap(queryParamMap);
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public NadeoResponse getResponse() {
        return this.response;
    }

    public void setResponse(NadeoResponse newResponse) {
        this.response = newResponse;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
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

    /**
     * Represents the expected response type corresponding to the current Request
     */
    public enum ResponseType {
        TIME(NadeoTimeResponse.class),
        POSITION(NadeoPositionResponse.class),
        MAP_INFO(NadeoMapResponse.class);


        private final Class<? extends NadeoResponse> clazz;

        ResponseType(Class<? extends NadeoResponse> clazz) {
            this.clazz = clazz;
        }

        public Class<? extends NadeoResponse> getClazz() {
            return clazz;
        }


    }

    /**
     * Enum to represent the different types of requests. Used by the plugin to more easily determined which results comes from which request.
     */
    public enum RequestType {
        POSITION(0),
        TIME(1),
        PB(2),
        MEDAL(3),
        OTHER(4);

        private final int reqType;

        RequestType(int reqType) {
            this.reqType = reqType;
        }

        public int getReqType() {
            return reqType;
        }
    }

}
