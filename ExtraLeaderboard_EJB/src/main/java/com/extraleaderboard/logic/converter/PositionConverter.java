package com.extraleaderboard.logic.converter;

import com.extraleaderboard.model.LeaderboardPosition;
import com.extraleaderboard.model.nadeoresponse.*;

public class PositionConverter implements Converter{

        public LeaderboardPosition convert(NadeoResponse nadeoResponse) {
                LeaderboardPosition pos;

                if(nadeoResponse != null && nadeoResponse instanceof NadeoPositionResponse){
                        //We are dealing with a position response, we assume that only one position is returned, i.e. tops is a list of size 1
                        NadeoPositionResponse nadPosResponse = (NadeoPositionResponse) nadeoResponse;
                        if (nadPosResponse.getTops() != null && nadPosResponse.getTops().size() == 1){
                                ZoneTop zoneTop = nadPosResponse.getTops().get(0);
                                if(zoneTop.getTop() != null && zoneTop.getTop().size() == 1){
                                        Top top = zoneTop.getTop().get(0);
                                        pos = new LeaderboardPosition(top.getScore(),top.getPosition(),top.getAccountId());
                                        return pos;
                                }
                        }
                }
                throw new IllegalArgumentException("The given NadeoResponse is not a NadeoPositionResponse");


        }
}
