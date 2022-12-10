package com.extraleaderboard.business.interfaces.map;

import com.extraleaderboard.model.UserResponse;

import javax.ejb.Local;

/**
 * Business handling all map related requests
 */
@Local
public interface MapBusinessLocal {

    /**
     * Get all information about a map (medal times, player count, ...)
     *
     * @param mapId id of the map
     * @return information about the map
     */
    UserResponse getAllMapInfo(String mapId);

    /**
     * Get the approximate amount of players on the leaderboard of a map
     *
     * @param mapId id of the map
     * @return amount of players on the leaderboard of the map
     * @apiNote this is only accurate below 10k player, after that it will be approximate to the 10k
     * (e.g. if you're 18956th, it will return 10k)
     */
    UserResponse getPlayerCount(String mapId);
}
