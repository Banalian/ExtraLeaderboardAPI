package com.extraleaderboard.api.map;

import com.extraleaderboard.api.map.medal.MedalResource;
import com.extraleaderboard.api.map.record.RecordResource;

import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Map resource, redirect to other resources related to leaderboard of this map
 */
public class MapResource {

    private final String mapId;

    @Inject
    public MapResource(String mapId) {
        this.mapId = mapId;
    }

    @Path("/medal")
    public MedalResource medal() {
        return new MedalResource(mapId);
    }

    /**
     * Get information on this map and leaderboard
     *
     * @return MapInfo
     */
    @GET
    public String allInfo() {
        return "Info for map " + mapId;
    }

    /**
     * Get the amount of players on this map leaderboard
     *
     * @return the amount of players on this map leaderboard
     */
    @GET
    @Path("/playercount")
    public String playerInfo() {
        return "Player info for map " + mapId;
    }

    /**
     * Return multiple records of this map leaderboard based of multiple parameters
     *
     */
    @Path("/records")
    public RecordResource records() {
        return new RecordResource(mapId);
    }
}