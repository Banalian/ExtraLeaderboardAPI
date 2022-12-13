package com.extraleaderboard.api.map.medal;

import com.extraleaderboard.business.implementation.map.medal.MedalBusinessImpl;
import com.extraleaderboard.business.interfaces.map.medal.MedalBusinessLocal;
import com.extraleaderboard.model.UserResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Medal resource, returns information about medals of a map
 */
public class MedalResource {

    //@EJB
    private final MedalBusinessLocal medalBusiness = new MedalBusinessImpl();

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
    @Produces("application/json")
    public UserResponse getAllMedals() {
        return medalBusiness.getAllMedals(mapId);
    }

    /**
     * Get the at medal time and hypothetical position in the leaderboard of the map
     *
     * @return the at medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/at")
    @Produces("application/json")
    public UserResponse atMedal() {
        return medalBusiness.getAtMedal(mapId);
    }

    /**
     * Get the gold medal time and hypothetical position in the leaderboard of the map
     *
     * @return the gold medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/gold")
    @Produces("application/json")
    public UserResponse goldMedal() {
        return medalBusiness.getGoldMedal(mapId);
    }

    /**
     * Get the silver medal time and hypothetical position in the leaderboard of the map
     *
     * @return the silver medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/silver")
    @Produces("application/json")
    public UserResponse silverMedal() {
        return medalBusiness.getSilverMedal(mapId);
    }

    /**
     * Get the bronze medal time and hypothetical position in the leaderboard of the map
     *
     * @return the bronze medal time and hypothetical position in the leaderboard of the map
     */
    @GET
    @Path("/bronze")
    @Produces("application/json")
    public UserResponse bronzeMedal() {
        return medalBusiness.getBronzeMedal(mapId);
    }

}