package com.extraleaderboard.api.map.postime;


import com.extraleaderboard.business.interfaces.map.postime.PositionBusinessLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

/**
 * Time resource, returns information about the position and time of a player on a map (based on the time requested)
 */
public class PositionResource {

    @EJB
    private PositionBusinessLocal positionBusiness;

    /**
     * ID of the map to get records from
     */
    private final String mapId;

    @Inject
    public PositionResource(String mapId) {
        this.mapId = mapId;
    }

    @GET
    public Object pos(@QueryParam("time") int time) {
        return positionBusiness.getPosition(mapId, time);
    }
}
