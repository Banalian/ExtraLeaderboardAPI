package com.extraleaderboard.model;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeoresponse.NadeoMapResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoPositionResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoTimeResponse;
import com.extraleaderboard.model.trackmania.EntryType;

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
    private final EntryType entryType;
    private NadeoResponse response;
    private Audience audience;

    /**
     * Main request constructor
     *
     * @param newUrl           the targeted url (including url params)
     * @param newQueryParamMap map of all query parameters
     * @param newResponseType  enum specifying the expected response type
     * @param entryType        enum specifying the entry type of the request
     */
    public Request(Audience audience, String newUrl, Map<String, Object> newQueryParamMap, ResponseType newResponseType, EntryType entryType) {
        this.audience = audience;
        this.endPoint = newUrl;
        this.queryParamMap = newQueryParamMap;
        this.responseType = newResponseType;
        this.entryType = entryType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public EntryType getEntryType() {
        return entryType;
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

}
