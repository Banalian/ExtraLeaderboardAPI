package com.extraleaderboard.model.nadeo;

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

    SURROUND("/api/token/leaderboard/group/Personal_Best/map/{mapid}/surround/{above}/{below}");


    private static final String BASE_URL = "https://live-services.trackmania.nadeo.live";

    private static final String AUDIENCE_NAME = "NadeoLiveServices";

    private final String url;

    NadeoLiveServices(String url) {
        this.url = url;
    }

    public String getUrl() {
        return BASE_URL + url;
    }

    public static String getAudienceName() {
        return AUDIENCE_NAME;
    }


}
