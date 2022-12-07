package com.extraleaderboard.api.map.postime;

import com.extraleaderboard.business.implementation.map.postime.TimeBusinessImpl;
import com.extraleaderboard.business.interfaces.map.postime.TimeBusinessLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

/**
 * Time resource, returns information about the position and time of a player on a map (based on the time requested)
 */
public class TimeResource {

    //@EJB
    private TimeBusinessLocal timeBusiness = new TimeBusinessImpl();

    /**
     * ID of the map to get records from
     */
    private final String mapId;

    @Inject
    public TimeResource(String mapId) {
        this.mapId = mapId;
    }

    @GET
    public Object time(@QueryParam("pos") int pos) {
        return timeBusiness.getTime(mapId, pos);
    }

}