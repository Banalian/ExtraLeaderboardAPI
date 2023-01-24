package com.extraleaderboard.model.nadeo;

import java.util.Map;

public enum NadeoLiveServices {

    /**
     * Get the leaderboard for a specific map.
     * <p>
     * URI parameters :
     *     <ul>
     *         <li>mapId : the map id</li>
     *     </ul>
     * Query parameters :
     *     <ul>
     *         <li>length : the number of entries to return</li>
     *         <li>offset : the offset of the first entry to return</li>
     *         <li>onlyWorld : if true, only the world records are returned. Else returns results for other leaderboard like the regional one</li>
     *     </ul>
     * </p>
     */
    MAP_LEADERBOARD("/api/token/leaderboard/group/Personal_Best/map/{mapId}/top"),

    /**
     * Get the position of a player or a time on a map
     * <p>
     * URI parameters :
     * <ul>
     *     <li>mapId : the map id</li>
     *     <li>above : amount of record above the result</li>
     *     <li>below : amount of record below the result</li>
     * </ul>
     * Query parameters :
     * <ul>
     *     <li>score : the score to get the position of</li>
     *     <li>onlyWorld : if true, only the world records are returned. Else returns results for other leaderboard like the regional one</li>
     * </ul>
     * </p>
     */
    SURROUND("/api/token/leaderboard/group/Personal_Best/map/{mapId}/surround/{below}/{above}"),

    /**
     * Get the information of a map
     * <p>
     * URI parameters :
     *     <ul>
     *         <li>mapId : the map id</li>
     *     </ul>
     * </p>
     */
    MAP_INFO("/api/token/map/{mapId}");

    private static final String BASE_URL = "https://live-services.trackmania.nadeo.live";

    private static final Audience AUDIENCE = Audience.NADEO_LIVE_SERVICES;
    private final String url;

    NadeoLiveServices(String url) {
        this.url = url;
    }

    public static String buildUrl(String url, Map<String, String> urlParameters) {
        String finalUrl = url;
        for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
            finalUrl = finalUrl.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return finalUrl;
    }

    public String getUrl() {
        return BASE_URL + url;
    }

    public Audience getAudience() {
        return AUDIENCE;
    }

}