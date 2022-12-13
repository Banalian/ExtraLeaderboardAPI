package com.extraleaderboard.logic.converter;

import com.extraleaderboard.model.LeaderboardPosition;
import com.extraleaderboard.model.nadeoresponse.*;

public class TimeConverter implements Converter {

    @Override
    public LeaderboardPosition convert(NadeoResponse nadeoResponse) {
        LeaderboardPosition pos;

        if(nadeoResponse instanceof NadeoTimeResponse nadTimeResponse){
            //We are dealing with a position response, we assume that only one position is returned, i.e. tops is a list of size 1
            if (nadTimeResponse.getTops() != null ){
                if(nadTimeResponse.getTops().size() == 1){
                    ZoneTop zoneTop = nadTimeResponse.getTops().get(0);
                    if (zoneTop.getTop() != null && zoneTop.getTop().size() == 1) {
                        Top top = zoneTop.getTop().get(0);
                        pos = new LeaderboardPosition(top.getScore(), top.getPosition(), top.getAccountId());
                        return pos;
                    }throw new IllegalArgumentException("The zoneTop attribute of the NadeoResponse contains a Top that is null or of length != 1");

                }throw new IllegalArgumentException("The given NadeoResponses Tops attribute is of a non conform size (size != 1)");
            }throw new IllegalArgumentException("The given NadeoResponses Tops attribute is null");
        }throw new IllegalArgumentException("The given NadeoResponse is null or not a NadeoTimeResponse");
    }
}
