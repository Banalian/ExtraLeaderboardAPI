package com.extraleaderboard.logic.handler;

import com.extraleaderboard.logic.converter.Converter;
import com.extraleaderboard.logic.converter.ConverterFactory;
import com.extraleaderboard.model.Payload;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.ResponseData;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;

import java.util.ArrayList;
import java.util.List;

public class UserResponseHandler implements Handler {


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
            currentResponse = request.getResponse();
            converter = ConverterFactory.getConverter(request.getResponseType());
            responseDataList.add(converter.convert(currentResponse));
        }

        payloadToHandle.setResponseDataList(responseDataList);
    }
}
