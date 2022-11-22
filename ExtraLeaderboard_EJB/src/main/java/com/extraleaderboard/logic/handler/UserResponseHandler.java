package com.extraleaderboard.logic.handler;

import com.extraleaderboard.model.Payload;
import com.extraleaderboard.model.Request;

import java.util.List;

public class UserResponseHandler implements Handler{


    /**
     * main Handling method, handles responses if the given payload is usable
     * @param payloadToHandle the payload containing the requests we want to extract responses from
     */
    @Override
    public void handle(Payload payloadToHandle) {
        if (isCorrectData(payloadToHandle)){
            handleUserResponses(payloadToHandle.getRequests());
        }
    }

    /**
     * Checks if the given payload exists and has a usable list of Request objects
     * @param payloadToCheck payload we want to handle
     * @return true if the payload is usable, false if it is not
     */
    public boolean isCorrectData(Payload payloadToCheck) {
        return payloadToCheck != null && payloadToCheck.getRequests() != null;
    }

    /**
     * Extracts responses from the Request objects and assigns corresponding UserResponses for each of them
     * @param requests list of request that we want the responses of
     */
    //TODO finish response -> userResponse translation
    public void handleUserResponses(List<Request> requests){
        for(Request request : requests){

        }
    }
}
