package com.extraleaderboard.logic.converter;


import com.extraleaderboard.model.ResponseData;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;

//TODO: Add documentation for Converters and the data types they use
public interface Converter {
    public ResponseData convert(NadeoResponse response);
}
