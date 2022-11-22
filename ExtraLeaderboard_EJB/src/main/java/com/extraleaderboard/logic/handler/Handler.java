package com.extraleaderboard.logic.handler;

import com.extraleaderboard.model.Payload;

/**
 * Handler interface for implementing in handlers in the responsibility chain
 */
public interface Handler {

    /**
     *
     * @param payloadToHandle the payload containing the requests we want to do stuff on
     */
    void handle(Payload payloadToHandle);


}
