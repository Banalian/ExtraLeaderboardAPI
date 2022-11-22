package com.extraleaderboard.business.implementation.map.record;

import com.extraleaderboard.business.interfaces.map.record.RecordBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;
import com.extraleaderboard.model.trackmania.Medal;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class RecordBusinessImpl implements RecordBusinessLocal {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getRecords(String mapId, List<Integer> scoreList, List<Integer> playerList, List<Medal> medalList, List<Integer> positionList, boolean getPlayerInfo) {
        // First step : getting the map information if medals are requested

        String urlSurround = generateUrlSurround(mapId);
        String urlTop = generateUrlTop(mapId);

        List<Request> requests = new ArrayList<>();

        if(medalList != null && !medalList.isEmpty()) {
            Object mapInfo = getMapInfo(mapId);
            Map<String,Object> queryParameters = new HashMap<>();
            queryParameters.put("onlyWorld", "true");
            for ( Medal medal : medalList ) {
                //TODO: get the score from the map info
                switch (medal) {
                    case AUTHOR -> queryParameters.put("score", 0);
                    case GOLD -> queryParameters.put("score", 1);
                    case SILVER -> queryParameters.put("score", 2);
                    case BRONZE -> queryParameters.put("score", 3);
                }
                requests.add(new Request(Audience.NADEO_LIVE_SERVICES, urlSurround, queryParameters, Request.ResponseType.POSITION));
            }
        }

        // Second step : adding all other requests

        if(scoreList != null && !scoreList.isEmpty()) {
            Map<String,Object> queryParameters = new HashMap<>();
            queryParameters.put("onlyWorld", "true");
            queryParameters.put("score", scoreList);

            Request request = new Request(Audience.NADEO_LIVE_SERVICES, urlSurround, queryParameters, Request.ResponseType.POSITION);
            requests.add(request);
        }

        // We don't check the player list for now
        // TODO: check the player list

        if(positionList != null && !positionList.isEmpty()) {

            for (Integer position : positionList) {
                Map<String,Object> queryParameters = new HashMap<>();
                queryParameters.put("onlyWorld", "true");
                queryParameters.put("length", 1);
                queryParameters.put("offset", position-1);

                Request request = new Request(Audience.NADEO_LIVE_SERVICES, urlTop, queryParameters, Request.ResponseType.TIME);
                requests.add(request);
            }
        }

        // If we want the playerCount, we need to add a request for it
        if(getPlayerInfo) {
            Map<String,Object> queryParameters = new HashMap<>();
            queryParameters.put("onlyWorld", "true");
            queryParameters.put("score", Integer.MAX_VALUE);

            Request request = new Request(Audience.NADEO_LIVE_SERVICES, urlSurround, queryParameters, Request.ResponseType.POSITION);
            requests.add(request);
        }


        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(requests);

        // Handle response and create the object
        return response;
    }

    private Object getMapInfo(String mapId){
        String finalUrlMapInfo = generateUrlMapInfo(mapId);
        Map<String, Object> queryParamMap = new HashMap<>();
        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlMapInfo, queryParamMap, Request.ResponseType.MAP_INFO);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));

        return response;
    }

    private String generateUrlMapInfo(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_INFO.getUrl(), urlParams);
    }

    private String generateUrlTop(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_LEADERBOARD.getUrl(), urlParams);
    }

    private String generateUrlSurround(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);
        urlParams.put("above", "0");
        urlParams.put("below", "0");

        return NadeoLiveServices.buildUrl(NadeoLiveServices.SURROUND.getUrl(), urlParams);
    }
}
