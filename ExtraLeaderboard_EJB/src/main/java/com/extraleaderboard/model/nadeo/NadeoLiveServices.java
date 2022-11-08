package com.extraleaderboard.model.nadeo;

public enum NadeoLiveServices implements INadeoService {

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
    MAP_LEADERBOARD("/api/token/leaderboard/group/Personal_Best/map/{mapId}/top", Object.class),

    SURROUND("/api/token/leaderboard/group/Personal_Best/map/{mapid}/surround/{above}/{below}", Object.class);


    private static final String BASE_URL = "https://live-services.trackmania.nadeo.live";

    private static final Audience AUDIENCE = Audience.NADEO_LIVE_SERVICES;

    private final Class responseClass;

    private final String url;

    NadeoLiveServices(String url, Class responseClass) {
        this.url = url;
        this.responseClass = responseClass;
    }

    public String getUrl() {
        return BASE_URL + url;
    }

    public static String getAudienceName() {
        return AUDIENCE.getAudience();
    }

    @Override
    public Class getResponseClass() {
        return responseClass;
    }

    @Override
    public Audience getAudience() {
        return AUDIENCE;
    }

}
