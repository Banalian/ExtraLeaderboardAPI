package com.extraleaderboard.business.implementation.map.record;

import com.extraleaderboard.business.interfaces.map.record.RecordBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.*;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;
import com.extraleaderboard.model.trackmania.Medal;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class RecordBusinessImpl implements RecordBusinessLocal {

    public static final String ONLY_WORLD_PARAM_NAME = "onlyWorld";
    public static final String SCORE_PARAM_NAME = "score";
    public static final String MAP_ID_URL_NAME = "mapId";

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getRecords(String mapId, List<Integer> scoreList, List<Integer> playerList, List<Medal> medalList, List<Integer> positionList, boolean getPlayerInfo, boolean getMapInfo) {
        // First step : getting the map information if medals are requested

        String urlSurround = generateUrlSurround(mapId);
        String urlTop = generateUrlTop(mapId);

        List<Request> requests = new ArrayList<>();

        // If we want the playerCount, we need to add a request for it and put it first in the list
        if (getPlayerInfo) {
            Map<String, Object> queryParameters = new HashMap<>();
            queryParameters.put(ONLY_WORLD_PARAM_NAME, "true");
            queryParameters.put(SCORE_PARAM_NAME, Integer.MAX_VALUE);

            Request request = new Request(Audience.NADEO_LIVE_SERVICES, urlSurround, queryParameters, Request.ResponseType.POSITION, Request.RequestType.OTHER);
            requests.add(request);
        }

        MapInfo mapInfo;

        if (medalList != null && !medalList.isEmpty()) {
            mapInfo = getMapInfo(mapId);
            for (Medal medal : medalList) {
                Map<String, Object> queryParameters = new HashMap<>();
                queryParameters.put(ONLY_WORLD_PARAM_NAME, "true");

                switch (medal) {
                    case AUTHOR -> queryParameters.put(SCORE_PARAM_NAME, mapInfo.getAuthorTime());
                    case GOLD -> queryParameters.put(SCORE_PARAM_NAME, mapInfo.getGoldTime());
                    case SILVER -> queryParameters.put(SCORE_PARAM_NAME, mapInfo.getSilverTime());
                    case BRONZE -> queryParameters.put(SCORE_PARAM_NAME, mapInfo.getBronzeTime());
                    default -> throw new IllegalStateException("Unexpected value: " + medal);
                }
                requests.add(new Request(Audience.NADEO_LIVE_SERVICES, urlSurround, queryParameters, Request.ResponseType.POSITION, Request.RequestType.MEDAL));
            }
        }

        // Second step : adding all other requests

        if (scoreList != null && !scoreList.isEmpty()) {
            for (Integer score : scoreList) {
                Map<String, Object> queryParameters = new HashMap<>();
                queryParameters.put(ONLY_WORLD_PARAM_NAME, "true");
                queryParameters.put(SCORE_PARAM_NAME, score);

                requests.add(new Request(Audience.NADEO_LIVE_SERVICES, urlSurround, queryParameters, Request.ResponseType.POSITION, Request.RequestType.TIME));
            }
        }

        // We don't check the player list for now
        // TODO: check the player list

        if (positionList != null && !positionList.isEmpty()) {
            for (Integer position : positionList) {
                Map<String, Object> queryParameters = new HashMap<>();
                queryParameters.put(ONLY_WORLD_PARAM_NAME, "true");
                queryParameters.put("length", 1);
                queryParameters.put("offset", position - 1);

                Request request = new Request(Audience.NADEO_LIVE_SERVICES, urlTop, queryParameters, Request.ResponseType.TIME, Request.RequestType.POSITION);
                requests.add(request);
            }
        }

        if (getMapInfo) {
            requests.add(new Request(Audience.NADEO_LIVE_SERVICES, generateUrlMapInfo(mapId), new HashMap<>(), Request.ResponseType.MAP_INFO, Request.RequestType.OTHER));
        }


        MainHandler mainHandler = new MainHandler();
        List<ResponseData> response = mainHandler.process(requests);

        // Handle response and create the object
        return createResponse(response, getPlayerInfo);
    }

    private UserResponse createResponse(List<ResponseData> response, boolean containsPlayerCount) {
        UserResponse userResponse = new UserResponse();

        if (containsPlayerCount) {
            LeaderboardPosition position = (LeaderboardPosition) response.get(0);
            userResponse.addMeta("playerCount", position.getRank());
            response.remove(0);
        }

        for (ResponseData responseData : response) {
            // Check the response type
            if (responseData instanceof LeaderboardPosition leaderboardPosition) {
                userResponse.addPosition(leaderboardPosition);
            }

            if (responseData instanceof MapInfo mapInfo) {
                userResponse.setMapInfo(mapInfo);
            }
        }
        return userResponse;
    }

    private MapInfo getMapInfo(String mapId) {
        String finalUrlMapInfo = generateUrlMapInfo(mapId);
        Map<String, Object> queryParamMap = new HashMap<>();
        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlMapInfo, queryParamMap, Request.ResponseType.MAP_INFO, Request.RequestType.OTHER);

        MainHandler mainHandler = new MainHandler();
        List<ResponseData> response = mainHandler.process(Collections.singletonList(request));

        return (MapInfo) response.get(0);
    }

    private String generateUrlMapInfo(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(MAP_ID_URL_NAME, mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_INFO.getUrl(), urlParams);
    }

    private String generateUrlTop(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(MAP_ID_URL_NAME, mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_LEADERBOARD.getUrl(), urlParams);
    }

    private String generateUrlSurround(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(MAP_ID_URL_NAME, mapId);
        urlParams.put("above", "0");
        urlParams.put("below", "0");

        return NadeoLiveServices.buildUrl(NadeoLiveServices.SURROUND.getUrl(), urlParams);
    }
}
