package com.extraleaderboard.logic.converter;


import com.extraleaderboard.model.ResponseData;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;

/**
 * Interface for all converters
 */
public interface Converter {
    ResponseData convert(NadeoResponse response);
}
