package com.extraleaderboard.logic.converter;

import com.extraleaderboard.model.Request;

public class ConverterFactory {

    public static Converter getConverter(Request.ResponseType responseType){

        switch (responseType){
            case POSITION:
                return new PositionConverter();
            case TIME:
                return new TimeConverter();
            case MAP_INFO:
                return new MapConverter();
            default:
                throw new IllegalArgumentException("The given responseType is not supported");
        }
    }
}
