package com.extraleaderboard.business.interfaces.map.medal;

import javax.ejb.Local;

/**
 * Business handling all medal related requests
 */
@Local
public interface MedalBusinessLocal {

    /**
     * Get all medal times and hypothetical positions in the leaderboard of a map
     *
     * @param mapId id of the map
     * @return all medal times and hypothetical positions in the leaderboard of the map
     */
    Object getAllMedals(String mapId);

    /**
     * Get the at medal time and hypothetical position in the leaderboard of a map
     *
     * @param mapId id of the map
     * @return the at medal time and hypothetical position in the leaderboard of the map
     */
    Object getAtMedal(String mapId);

    /**
     * Get the gold medal time and hypothetical position in the leaderboard of a map
     *
     * @param mapId id of the map
     * @return the gold medal time and hypothetical position in the leaderboard of the map
     */
    Object getGoldMedal(String mapId);

    /**
     * Get the silver medal time and hypothetical position in the leaderboard of a map
     *
     * @param mapId id of the map
     * @return the silver medal time and hypothetical position in the leaderboard of the map
     */
    Object getSilverMedal(String mapId);

    /**
     * Get the bronze medal time and hypothetical position in the leaderboard of a map
     *
     * @param mapId id of the map
     * @return the bronze medal time and hypothetical position in the leaderboard of the map
     */
    Object getBronzeMedal(String mapId);
}
