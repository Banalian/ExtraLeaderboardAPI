package com.extraleaderboard.business.implementation.map.medal;

import com.extraleaderboard.business.interfaces.map.medal.MedalBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.*;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;
import com.extraleaderboard.model.trackmania.Medal;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class MedalBusinessImpl implements MedalBusinessLocal {
    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getAllMedals(String mapId) {
        String finalUrlSurround = generateUrlSurround(mapId);

        MapInfo mapInfo = getMapInfo(mapId);

        List<Request> requests = new ArrayList<>();

        for (Medal medal : Medal.values()) {
            if(medal == Medal.NONE){
                continue;
            }
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("onlyWorld", "true");
            switch (medal) {
                case AUTHOR -> parameters.put("score", mapInfo.getAuthorTime());
                case GOLD -> parameters.put("score", mapInfo.getGoldTime());
                case SILVER -> parameters.put("score", mapInfo.getSilverTime());
                case BRONZE -> parameters.put("score", mapInfo.getBronzeTime());
            }
            Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlSurround, parameters, Request.ResponseType.POSITION);
            requests.add(request);
        }

        MainHandler mainHandler = new MainHandler();
        List<ResponseData> responseDataList = mainHandler.process(requests);

        UserResponse userResponse = new UserResponse();

        // transform the response data list into a leaderboardposition list
        for (ResponseData responseData : responseDataList) {
            LeaderboardPosition leaderboardPosition = (LeaderboardPosition) responseData;
            userResponse.addPosition(leaderboardPosition);
        }
        
        return userResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getAtMedal(String mapId) {
        MapInfo mapInfo = getMapInfo(mapId);
        LeaderboardPosition atMedal = getTimeOfMedal(mapId, mapInfo.getAuthorTime());

        UserResponse userResponse = new UserResponse();
        userResponse.addPosition(atMedal);
        return userResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getGoldMedal(String mapId) {
        MapInfo mapInfo = getMapInfo(mapId);
        LeaderboardPosition goldMedal = getTimeOfMedal(mapId, mapInfo.getGoldTime());

        UserResponse userResponse = new UserResponse();
        userResponse.addPosition(goldMedal);
        return userResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getSilverMedal(String mapId) {
        MapInfo mapInfo = getMapInfo(mapId);
        LeaderboardPosition silverMedal = getTimeOfMedal(mapId, mapInfo.getSilverTime());

        UserResponse userResponse = new UserResponse();
        userResponse.addPosition(silverMedal);
        return userResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse getBronzeMedal(String mapId) {
        MapInfo mapInfo = getMapInfo(mapId);
        LeaderboardPosition bronzeMedal = getTimeOfMedal(mapId, mapInfo.getBronzeTime());

        UserResponse userResponse = new UserResponse();
        userResponse.addPosition(bronzeMedal);
        return userResponse;
    }

    private MapInfo getMapInfo(String mapId){
        String finalUrlMapInfo = generateUrlMapInfo(mapId);
        Map<String, Object> queryParamMap = new HashMap<>();
        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlMapInfo, queryParamMap, Request.ResponseType.MAP_INFO);

        MainHandler mainHandler = new MainHandler();
        List<ResponseData> response = mainHandler.process(Collections.singletonList(request));

        return (MapInfo) response.get(0);
    }

    private LeaderboardPosition getTimeOfMedal(String mapId, int score){
        String finalUrlSurround = generateUrlSurround(mapId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("onlyWorld", "true");
        parameters.put("score", score);

        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlSurround, parameters, Request.ResponseType.POSITION);

        MainHandler mainHandler = new MainHandler();
        List<ResponseData> response = mainHandler.process(Collections.singletonList(request));

        return (LeaderboardPosition) response.get(0);
    }

    private String generateUrlMapInfo(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_INFO.getUrl(), urlParams);
    }

    private String generateUrlSurround(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);
        urlParams.put("above", "0");
        urlParams.put("below", "0");

        return NadeoLiveServices.buildUrl(NadeoLiveServices.SURROUND.getUrl(), urlParams);
    }
}
