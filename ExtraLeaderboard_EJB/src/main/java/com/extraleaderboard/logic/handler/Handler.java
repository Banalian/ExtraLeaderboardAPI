package com.extraleaderboard.logic.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Handler {

    //TODO change type of param and return here, probably not going to be String
    public ObjectNode format(ObjectNode request);

}
