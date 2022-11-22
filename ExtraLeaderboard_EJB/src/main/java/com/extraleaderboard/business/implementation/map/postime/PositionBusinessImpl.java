package com.extraleaderboard.business.implementation.map.postime;

import com.extraleaderboard.business.interfaces.map.postime.PositionBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class PositionBusinessImpl implements PositionBusinessLocal {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPosition(String mapId, int time) {
        String finalUrl = generateUrl(mapId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("score", time);
        parameters.put("onlyWorld", "true");

        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, parameters, Request.ResponseType.POSITION);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));

        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPositions(String mapId, List<Integer> times) {
        String finalUrl = generateUrl(mapId);

        List<Request> requests = new ArrayList<>();

        for (int time : times) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("score", time);
            parameters.put("onlyWorld", "true");

            Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, parameters, Request.ResponseType.POSITION);
            requests.add(request);
        }

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(requests);


        return response;
    }

    private String generateUrl(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("above", "0");
        urlParams.put("below", "0");
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.SURROUND.getUrl(), urlParams);
    }
}
