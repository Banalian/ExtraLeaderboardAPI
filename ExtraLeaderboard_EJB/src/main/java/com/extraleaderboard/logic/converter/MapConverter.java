package com.extraleaderboard.logic.converter;

import com.extraleaderboard.model.MapInfo;
import com.extraleaderboard.model.nadeoresponse.NadeoMapResponse;
import com.extraleaderboard.model.nadeoresponse.NadeoResponse;


public class MapConverter implements Converter {

    public MapInfo convert(NadeoResponse nadeoResponse) {
        if (nadeoResponse instanceof NadeoMapResponse nadMapResponse) {
            //We are dealing with a map response, we assume that the tops list is empty, we just want the info about the map
            if(nadMapResponse != null){
                MapInfo mapInfo = new MapInfo();

                //general info
                mapInfo.setUid(nadMapResponse.getUid());
                mapInfo.setName(nadMapResponse.getName());
                mapInfo.setAuthor(nadMapResponse.getAuthor());
                mapInfo.setNbLaps(nadMapResponse.getNbLaps());
                mapInfo.setSubmitter(nadMapResponse.getSubmitter());

                //medal times
                mapInfo.setAuthorTime(nadMapResponse.getAuthorTime());
                mapInfo.setGoldTime(nadMapResponse.getGoldTime());
                mapInfo.setSilverTime(nadMapResponse.getSilverTime());
                mapInfo.setBronzeTime(nadMapResponse.getBronzeTime());

                return mapInfo;
            }else{
                throw new IllegalArgumentException("The given NadeoResponse is null, this is not supported");
            }
        }
        throw new IllegalArgumentException("The given NadeoResponse is not a NadeoMapResponse");
    }

}
