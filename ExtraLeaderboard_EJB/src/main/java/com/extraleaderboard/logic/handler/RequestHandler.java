package com.extraleaderboard.logic.handler;

import com.extraleaderboard.logic.task.NadeoHttpTask;
import com.extraleaderboard.model.Payload;
import com.extraleaderboard.model.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Is in charge of getting responses from the Nadeo API.
 */
public class RequestHandler implements Handler {

    /**
     * main Handling method, handles requests if the given payload is usable
     *
     * @param payloadToHandle the payload containing the requests we want to execute
     */
    @Override
    public void handle(Payload payloadToHandle) {
        if (isCorrectData(payloadToHandle)) {
            handleRequests(payloadToHandle.getRequests());
        }
    }

    /**
     * Checks if the payload exists and has a usable list of requests
     *
     * @param payloadToCheck the payload we want to validate
     * @return true if the payload is usable, false if it is not
     */
    public boolean isCorrectData(Payload payloadToCheck) {
        return payloadToCheck != null && payloadToCheck.getRequests() != null;
    }

    /**
     * Handles requests, makes a thread to execute each request and get its response
     *
     * @param requests the list of requests we want to get responses for
     */
    public void handleRequests(List<Request> requests) {
        List<Thread> threads = new ArrayList<>();

        for (Request request : requests) {
            NadeoHttpTask task = new NadeoHttpTask();
            task.setRequest(request);

            Thread thread = new Thread(task);

            threads.add(thread);

            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
