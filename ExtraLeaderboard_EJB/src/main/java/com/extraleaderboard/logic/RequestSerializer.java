package com.extraleaderboard.logic;

import com.extraleaderboard.model.Request;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

public class RequestSerializer extends JsonSerializer<Request> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(Request request,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, request);
        jsonGenerator.writeFieldName(writer.toString());

    }
}
