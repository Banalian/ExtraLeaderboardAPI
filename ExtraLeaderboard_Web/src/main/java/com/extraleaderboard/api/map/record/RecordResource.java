package com.extraleaderboard.api.map.record;

import com.extraleaderboard.business.implementation.map.record.RecordBusinessImpl;
import com.extraleaderboard.business.interfaces.map.record.RecordBusinessLocal;
import com.extraleaderboard.model.UserResponse;
import com.extraleaderboard.model.trackmania.Medal;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Record resource, single endpoint for getting records based on multiple parameters
 */
public class RecordResource {

    //@EJB
    private final RecordBusinessLocal recordBusiness = new RecordBusinessImpl();

    /**
     * ID of the map to get records from
     */
    private final String mapId;

    public RecordResource(String mapId) {
        this.mapId = mapId;
    }

    /**
     * Get records based on multiple parameters
     * the list parameters are always separated by a comma
     *
     * @param scoreList      list of times on the map, used to return the position of these times
     * @param playerList     list of players, used to return the position/times of these players
     * @param medalList      list of medals, used to return the position of these medals
     * @param positionList   list of positions, used to return the times of these positions
     * @param getPlayerCount true or false, used to return the amount of players on the leaderboard. Default is false.
     *                       If any other value is given, it will be considered as true
     * @return an JSON object with the requested information, with a list of records, and other potential information.
     * The returned JSON might also contain some warning or error messages
     * if the parameters are not valid or an error occurred
     */
    @GET
    @Produces("application/json")
    public UserResponse getRecords(@QueryParam("score") String scoreList,
                                   @QueryParam("player") String playerList,
                                   @QueryParam("medal") String medalList,
                                   @QueryParam("position") String positionList,
                                   @DefaultValue("false")
                                   @QueryParam("getplayercount") String getPlayerCount,
                                   @DefaultValue("false")
                                   @QueryParam("getmapinfo") String getMapInfo) {
        // Transform all lists to List<Integer>
        List<Integer> scoreListInt = getListFromString(scoreList);
        List<Integer> playerListInt = getListFromString(playerList);
        List<Integer> positionListInt = getListFromString(positionList);

        List<Integer> medalListInt = getListFromString(medalList);
        // Transform the medal list to a list of medals
        List<Medal> medalListMedal = medalListInt.stream()
                .map(Medal::getMedalFromValue)
                .toList();

        // Check if getPlayerCount is true
        boolean getPlayerCountBool = !"false".equals(getPlayerCount);
        boolean getMapInfoBool = !"false".equals(getMapInfo);

        // Get the records
        return recordBusiness.getRecords(mapId, scoreListInt, playerListInt, medalListMedal, positionListInt, getPlayerCountBool, getMapInfoBool);
    }

    /**
     * Transform a string to a list of integers
     *
     * @param list the string to transform
     * @return the list of integers
     */
    private List<Integer> getListFromString(String list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(list.split(","))
                .map(Integer::parseInt)
                .toList();
    }
}
