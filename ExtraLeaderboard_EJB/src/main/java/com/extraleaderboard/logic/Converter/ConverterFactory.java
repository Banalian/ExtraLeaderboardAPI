package com.extraleaderboard.logic.converter;

import com.extraleaderboard.model.Request;

/**
 * Represents
 */
public class ConverterFactory {

    public static Converter getConverter(Request.ResponseType responseType){

        return switch (responseType) {
            case POSITION -> new PositionConverter();
            case TIME -> new TimeConverter();
            case MAP_INFO -> new MapConverter();
            default -> throw new IllegalArgumentException("The given responseType is not supported");
        };
    }
}
