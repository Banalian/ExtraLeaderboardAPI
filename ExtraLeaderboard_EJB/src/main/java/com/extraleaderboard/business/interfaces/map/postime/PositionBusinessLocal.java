package com.extraleaderboard.business.interfaces.map.postime;

import com.extraleaderboard.model.UserResponse;

import javax.ejb.Local;
import java.util.List;

/**
 * Business handling all position related requests
 */
@Local
public interface PositionBusinessLocal {

    /**
     * Get the position of a player on a map based on the time requested
     *
     * @param mapId id of the map
     * @param time  time to get the position of
     * @return position of the player on the map
     */
    Object getPosition(String mapId, int time);


    /**
     * Get the position of a list of times on a map
     *
     * @param mapId id of the map
     * @param times list of times to get the position of
     * @return list of positions of the players on the map
     */
    Object getPositions(String mapId, List<Integer> times);
}
