package com.extraleaderboard.api.map.medal;

import com.extraleaderboard.business.implementation.map.medal.MedalBusinessImpl;
import com.extraleaderboard.business.interfaces.map.medal.MedalBusinessLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Medal resource, returns information about medals of a map
 */
public class MedalResource {

    //@EJB
    private MedalBusinessLocal medalBusiness = new MedalBusinessImpl();

    /**
     * Map id, needed to get the right information
     */
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
    public Object getAllMedals() {
        return medalBusiness.getAllMedals(mapId);
    }

    /**
     * Get the at medal time and hypothetical position in the leaderboard of the map
     *
     * @return the at medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/at")
    public Object atMedal() {
        return medalBusiness.getAtMedal(mapId);
    }

    /**
     * Get the gold medal time and hypothetical position in the leaderboard of the map
     *
     * @return the gold medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/gold")
    public Object goldMedal() {
        return medalBusiness.getGoldMedal(mapId);
    }

    /**
     * Get the silver medal time and hypothetical position in the leaderboard of the map
     *
     * @return the silver medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/silver")
    public Object silverMedal() {
        return medalBusiness.getSilverMedal(mapId);
    }

    /**
     * Get the bronze medal time and hypothetical position in the leaderboard of the map
     *
     * @return the bronze medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/bronze")
    public Object bronzeMedal() {
        return medalBusiness.getBronzeMedal(mapId);
    }

}