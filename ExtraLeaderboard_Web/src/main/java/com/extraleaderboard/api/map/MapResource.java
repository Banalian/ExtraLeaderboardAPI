package com.extraleaderboard.api.map;

import com.extraleaderboard.api.map.medal.MedalResource;
import com.extraleaderboard.api.map.postime.PositionResource;
import com.extraleaderboard.api.map.postime.TimeResource;
import com.extraleaderboard.api.map.record.RecordResource;
import com.extraleaderboard.business.implementation.map.MapBusinessImpl;
import com.extraleaderboard.business.interfaces.map.MapBusinessLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Map resource, redirect to other resources related to leaderboard of this map
 */
public class MapResource {

    //@EJB
    private MapBusinessLocal mapBusiness = new MapBusinessImpl();

    /**
     * Map id, needed to get the right information
     */
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
    public Object allInfo() {
        return mapBusiness.getAllMapInfo(mapId);
    }

    /**
     * Get the amount of players on this map leaderboard
     *
     * @return the amount of players on this map leaderboard
     */
    @GET
    @Path("/playercount")
    public Object playerInfo() {
        return mapBusiness.getPlayerCount(mapId);
    }

    /**
     * Return multiple records of this map leaderboard based of multiple parameters
     */
    @Path("/records")
    public RecordResource records() {
        return new RecordResource(mapId);
    }

    /**
     * Return record(s) based on the time requested
     */
    @Path("/position")
    public TimeResource time() {
        return new TimeResource(mapId);
    }

    /**
     * Return record(s) based on the position requested
     */
    @Path("/time")
    public PositionResource position() {
        return new PositionResource(mapId);
    }
}