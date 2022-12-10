package com.extraleaderboard.api.map.postime;


import com.extraleaderboard.business.implementation.map.postime.PositionBusinessImpl;
import com.extraleaderboard.business.interfaces.map.postime.PositionBusinessLocal;
import com.extraleaderboard.model.UserResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Position resource, returns information about the position and time of a player on a map (based on the time requested)
 */
public class PositionResource {

    //@EJB
    private final PositionBusinessLocal positionBusiness = new PositionBusinessImpl();

    /**
     * ID of the map to get records from
     */
    private final String mapId;

    @Inject
    public PositionResource(String mapId) {
        this.mapId = mapId;
    }

    @GET
    @Produces("application/json")
    public UserResponse pos(@QueryParam("time") int time) {
        return positionBusiness.getPosition(mapId, time);
    }
}
