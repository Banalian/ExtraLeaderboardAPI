package com.extraleaderboard.logic.handler;

import com.extraleaderboard.model.Payload;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.ResponseData;

import java.util.List;

/**
 * Main handler of the responsibility chain, take an input, process it through the chain and return the result
 */
public class MainHandler {

    /**
     * Request handler used to make the requests to the Nadeo API
     */
    private final Handler requestHandler;

    /**
     * Response handler used to process the responses from the Nadeo API
     */
    private final Handler responseHandler;

    public MainHandler() {
        this.requestHandler = new RequestHandler();
        this.responseHandler = new UserResponseHandler();
    }

    /**
     * Process the payload through the chain
     *
     * @param requests the list of requests to process
     * @return the result of the processing
     */
    public List<ResponseData> process(List<Request> requests) {
        Payload payload = new Payload();
        payload.setRequests(requests);

        requestHandler.handle(payload);

        responseHandler.handle(payload);

        return payload.getResponseDataList();
    }
}
