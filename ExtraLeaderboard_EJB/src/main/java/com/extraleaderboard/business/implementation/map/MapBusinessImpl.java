package com.extraleaderboard.business.implementation.map;

import com.extraleaderboard.business.interfaces.map.MapBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;

import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class MapBusinessImpl implements MapBusinessLocal {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAllMapInfo(String mapId) {
        String finalUrl = generateUrlMapInfo(mapId);
        Map<String, Object> queryParamMap = new HashMap<>();
        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, queryParamMap, Request.ResponseType.MAP_INFO);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));

        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPlayerCount(String mapId) {
        String finalUrl = generateUrlSurround(mapId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("onlyWorld", "true");
        // Max value of a 32 bits integer
        parameters.put("score", Integer.MAX_VALUE);

        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, parameters, Request.ResponseType.POSITION);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));
        return response;
    }

    private String generateUrlSurround(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.SURROUND.getUrl(), urlParams);
    }

    private String generateUrlMapInfo(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_INFO.getUrl(), urlParams);
    }
}
