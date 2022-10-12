package com.extraleaderboard.api.map.medal;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Medal resource, returns information about medals of a map
 */
public class MedalResource {

    private final String mapId;

    @Inject
    public MedalResource(String mapId) {
        this.mapId = mapId;
    }

    /**
     * Get all medals time and hypothetical positions in the leaderboard of the map
     *
     * @return all medals time and hypothetical positions in the leaderboard of the map
     */
    @GET
    public String getAllMedals() {
        return "All medals for map " + mapId;
    }

    /**
     * Get the at medal time and hypothetical position in the leaderboard of the map
     *
     * @return the at medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/at")
    public String atMedal() {
        return "Medal at " + mapId;
    }

    /**
     * Get the gold medal time and hypothetical position in the leaderboard of the map
     *
     * @return the gold medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/gold")
    public String goldMedal() {
        return "Gold medal for map " + mapId;
    }

    /**
     * Get the silver medal time and hypothetical position in the leaderboard of the map
     *
     * @return the silver medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/silver")
    public String silverMedal() {
        return "Silver medal for map " + mapId;
    }

    /**
     * Get the bronze medal time and hypothetical position in the leaderboard of the map
     *
     * @return the bronze medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/bronze")
    public String bronzeMedal() {
        return "Bronze medal for map " + mapId;
    }

}