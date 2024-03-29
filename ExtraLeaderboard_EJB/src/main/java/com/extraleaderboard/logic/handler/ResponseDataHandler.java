package com.extraleaderboard.logic.handler;

import com.extraleaderboard.logic.converter.Converter;
import com.extraleaderboard.logic.converter.ConverterFactory;
import com.extraleaderboard.model.LeaderboardPosition;
import com.extraleaderboard.model.Payload;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.ResponseData;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;

import java.util.ArrayList;
import java.util.List;


public class ResponseDataHandler implements Handler{


    /**
     * main Handling method, handles responses if the given payload is usable
     *
     * @param payloadToHandle the payload containing the requests we want to extract responses from
     */
    @Override
    public void handle(Payload payloadToHandle) {
        if (isCorrectData(payloadToHandle)) {
            handleUserResponses(payloadToHandle);
        }
    }

    /**
     * Checks if the given payload exists and has a usable list of Request objects
     *
     * @param payloadToCheck payload we want to handle
     * @return true if the payload is usable, false if it is not
     */
    public boolean isCorrectData(Payload payloadToCheck) {
        return payloadToCheck != null && payloadToCheck.getRequests() != null;
    }

    /**
     * Extracts responses from the Request objects and assigns corresponding UserResponses for each of them
     *
     * @param payloadToHandle payload containing the list of request that we want the responses of
     */
    public void handleUserResponses(Payload payloadToHandle) {
        List<Request> requests = payloadToHandle.getRequests();
        Converter converter;
        NadeoResponse currentResponse;
        List<ResponseData> responseDataList = new ArrayList<>();

        for (Request request : requests) {
            try{
                currentResponse = request.getResponse();
                converter = ConverterFactory.getConverter(request.getResponseType());
                ResponseData responseData = converter.convert(currentResponse);
                // We're breaking some SOLID rule here, but I do not wish to change a big part
                // of the structure to add this single functionality
                if(responseData instanceof LeaderboardPosition entryResponse){
                    entryResponse.setEntryType(request.getEntryType());
                }
                responseDataList.add(responseData);
            } catch(IllegalArgumentException e){
                // TODO: add an error response to the list based on the request/response
                // For now we don't do anything with the error and just continue the loop
            }

        }

        payloadToHandle.setResponseDataList(responseDataList);
    }
}
