package com.extraleaderboard.logic.task;

import com.extraleaderboard.logic.NadeoHttpRequest;
import com.extraleaderboard.logic.exception.NadeoAPIResponseException;
import com.extraleaderboard.logic.exception.NadeoRuntimeException;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;
import com.extraleaderboard.model.Request;

import java.util.Map;

/**
 * Task for sending a request to the Nadeo API
 */
public class NadeoHttpTask implements Runnable {


    private Request request;
    private NadeoHttpRequest nadeoRequest;

    /**
     *
     * @return the request of the current task
     */
    public Request getRequest() {
        return request;
    }

    /**
     *
     * @param request
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Makes a thread that sends the corresponding request to the Nadeo API and assigns the received response to it
     */
    @Override
    public void run() {

        //audience
        nadeoRequest = new NadeoHttpRequest(request.getAudience());
        //parameters
        for(Map.Entry<String, Object> entry : request.getQueryParamMap().entrySet()){
            nadeoRequest.setParameter(entry.getKey(), entry.getValue());
        }
        //url
        nadeoRequest.setUrl(request.getEndPoint());

        nadeoRequest.setReturnClass(request.getResponseType().getClazz());

        try {
            NadeoResponse objectResponse = nadeoRequest.execute();
            request.setResponse(objectResponse);
        } catch (NadeoAPIResponseException e) {
            throw new NadeoRuntimeException(e);
        }

    }


}
