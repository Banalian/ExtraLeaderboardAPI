package com.extraleaderboard.business.interfaces.map.postime;

import com.extraleaderboard.model.UserResponse;

import javax.ejb.Local;
import java.util.List;

/**
 * Business handling all time related requests
 */
@Local
public interface TimeBusinessLocal {

    /**
     * Get the position of a player on a map based on the time requested
     *
     * @param mapId    id of the map
     * @param position position to get the position of
     * @return time of the player on the map
     */
    UserResponse getTime(String mapId, int position);


    /**
     * Get the position of a list of times on a map
     *
     * @param mapId     id of the map
     * @param positions list of positions to get the position of
     * @return list of times of the players on the map
     */
    UserResponse getTimes(String mapId, List<Integer> positions);
}