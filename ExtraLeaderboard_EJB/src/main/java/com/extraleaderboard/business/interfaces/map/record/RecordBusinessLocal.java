package com.extraleaderboard.business.interfaces.map.record;

import com.extraleaderboard.model.UserResponse;
import com.extraleaderboard.model.trackmania.Medal;

import javax.ejb.Local;
import java.util.List;

/**
 * Business handling all record related requests. This is the "get all in one spot" endpoint
 */
@Local
public interface RecordBusinessLocal {

    /**
     * Get multiple records based on multiple parameters
     *
     * @param mapId         id of the map
     * @param scoreList     list of times on the map, used to return the position of these times
     * @param playerList    list of players, used to return the position/times of these players
     * @param medalList     list of medals, used to return the position of these medals
     * @param positionList  list of positions, used to return the times of these positions
     * @param getPlayerInfo true or false, used to return the amount of players on the leaderboard. Default is false.
     *                      If any other value is given, it will be considered as true
     * @param getMapInfo    true or false, used to return the map information. Default is false.
     *                      If any other value is given, it will be considered as true
     * @return an JSON object with the requested information, with a list of records, and other potential information.
     * The returned JSON might also contain some warning or error messages
     * if the parameters are not valid or an error occurred
     */
    UserResponse getRecords(String mapId, List<Integer> scoreList, List<Integer> playerList, List<Medal> medalList, List<Integer> positionList, boolean getPlayerInfo, boolean getMapInfo);

}
