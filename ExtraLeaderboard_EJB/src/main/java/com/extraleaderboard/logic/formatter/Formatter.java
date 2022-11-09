package com.extraleaderboard.logic.formatter;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Formatter {

    public ObjectNode format(ObjectNode formatee);
}
