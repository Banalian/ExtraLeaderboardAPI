package com.extraleaderboard.business.implementation.map;

import com.extraleaderboard.business.interfaces.map.MapBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.*;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;
import com.extraleaderboard.model.trackmania.EntryType;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class MapBusinessImpl implements MapBusinessLocal {

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getAllMapInfo(String mapId) {
        String finalUrl = generateUrlMapInfo(mapId);
        Map<String, Object> queryParamMap = new HashMap<>();
        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, queryParamMap, Request.ResponseType.MAP_INFO, EntryType.OTHER);

        MainHandler mainHandler = new MainHandler();
        List<ResponseData> response = mainHandler.process(Collections.singletonList(request));

        // Extract the MapInfo from the response
        MapInfo mapInfo = (MapInfo) response.get(0);

        // Create the user response
        UserResponse userResponse = new UserResponse();
        userResponse.setMapInfo(mapInfo);


        return userResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getPlayerCount(String mapId) {
        String finalUrl = generateUrlSurround(mapId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("onlyWorld", "true");
        // Max value of a 32 bits integer
        parameters.put("score", Integer.MAX_VALUE);

        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, parameters, Request.ResponseType.POSITION, EntryType.OTHER);

        MainHandler mainHandler = new MainHandler();
        List<ResponseData> response = mainHandler.process(Collections.singletonList(request));

        // Get the LeaderBoardPosition from the response and put it in a UserResponse object
        UserResponse userResponse = new UserResponse();
        LeaderboardPosition leaderboardPosition = (LeaderboardPosition) response.get(0);

        // the position is the player count, extract it and put it in the meta info of the user response
        int playerCount = leaderboardPosition.getRank();
        // if the player count is below 100k, remove 1 to get the real player count
        // we don't do this above 100k since the results are approximated to the 10k-100k after that
        if (playerCount < 100000) {
            playerCount--;
        }
        userResponse.addMeta("playerCount", playerCount);

        return userResponse;
    }

    private String generateUrlSurround(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);
        urlParams.put("above", "0");
        urlParams.put("below", "0");

        return NadeoLiveServices.buildUrl(NadeoLiveServices.SURROUND.getUrl(), urlParams);
    }

    private String generateUrlMapInfo(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_INFO.getUrl(), urlParams);
    }
}
