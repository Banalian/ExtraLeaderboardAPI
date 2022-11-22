package com.extraleaderboard.business.implementation.map.medal;

import com.extraleaderboard.business.interfaces.map.medal.MedalBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class MedalBusinessImpl implements MedalBusinessLocal {
    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAllMedals(String mapId) {
        Object MapInfo = getMapInfo(mapId);

        List<Object> medals = new ArrayList<>();
        // TODO: extract the medals from the map info
        medals.add(getTimeOfMedal(mapId, 0));
        medals.add(getTimeOfMedal(mapId, 1));
        medals.add(getTimeOfMedal(mapId, 2));
        medals.add(getTimeOfMedal(mapId, 3));

        return medals;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAtMedal(String mapId) {
        Object mapInfo = getMapInfo(mapId);
        Object atMedal = getTimeOfMedal(mapId, 0);//TODO: get the at medal time from the map info

        return atMedal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getGoldMedal(String mapId) {
        Object mapInfo = getMapInfo(mapId);
        Object goldMedal = getTimeOfMedal(mapId, 0);//TODO: get the gold medal time from the map info

        return goldMedal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getSilverMedal(String mapId) {
        Object mapInfo = getMapInfo(mapId);
        Object silverMedal = getTimeOfMedal(mapId, 0);//TODO: get the silver medal time from the map info

        return silverMedal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getBronzeMedal(String mapId) {
        Object mapInfo = getMapInfo(mapId);
        Object bronzeMedal = getTimeOfMedal(mapId, 0);//TODO: get the bronze medal time from the map info

        return bronzeMedal;
    }

    private Object getMapInfo(String mapId){
        String finalUrlMapInfo = generateUrlMapInfo(mapId);
        Map<String, Object> queryParamMap = new HashMap<>();
        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlMapInfo, queryParamMap, Request.ResponseType.MAP_INFO);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));

        return response;
    }

    private Object getTimeOfMedal(String mapId, int score){
        String finalUrlSurround = generateUrlSurround(mapId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("onlyWorld", "true");
        parameters.put("score", score);

        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrlSurround, parameters, Request.ResponseType.POSITION);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));

        return response;
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
