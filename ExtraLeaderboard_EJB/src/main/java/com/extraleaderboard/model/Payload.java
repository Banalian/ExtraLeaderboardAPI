package com.extraleaderboard.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents a request payload, contains a list of all the requests we wish to send to the Nadeo API, and all corresponding responses we want
 * to relay to the user.
 */
public class Payload {

    /**
     * list of all requests, each request carries its NadeoResponse
     */
    private List<Request> requests;

    /**
     * list that will contain the response data corresponding to the requests in the requests list
     * these are to be passed to the main handler to be converted into UserResponses
     */
    private List<ResponseData> responseDataList;

    /**
     * @return the request list of the current Payload
     */
    public List<Request> getRequests() {
        return Collections.unmodifiableList(requests);
    }

    /**
     * @param newRequests new list of requests to be set
     */
    public void setRequests(List<Request> newRequests) {
        requests = newRequests;
    }

    /**
     * @param index index of the request we want to get
     * @return the request at the index in the request list of the current Payload
     */
    public Request getRequest(int index) {
        return requests.get(index).clone();
    }

    /**
     * @return the list of ResponseData of the current Payload
     */
    public List<ResponseData> getResponseDataList() {
        return responseDataList;
    }

    /**
     * @param newResponseDataList new list of ResponseData to be set
     */
    public void setResponseDataList(List<ResponseData> newResponseDataList) {
        this.responseDataList = newResponseDataList;
    }

}
