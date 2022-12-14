package com.extraleaderboard.api;

import com.extraleaderboard.api.map.MapResource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/leaderboard")
public class LeaderboardResource {

    @Path("/map/{mapId}")
    public MapResource getMapResource(@PathParam("mapId") String mapId) {
        return new MapResource(mapId);
    }
}