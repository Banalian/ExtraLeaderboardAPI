package com.extraleaderboard.api.map.record;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

/**
 * Record resource, single endpoint for getting records based on multiple parameters
 */
public class RecordResource {

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
    public String getRecords(@QueryParam("score") String scoreList,
                             @QueryParam("player") String playerList,
                             @QueryParam("medal") String medalList,
                             @QueryParam("position") String positionList,
                             @DefaultValue("false")
                             @QueryParam("getplayercount") String getPlayerCount) {
        return "Records for map " + mapId;
    }
}
