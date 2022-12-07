package com.extraleaderboard.business.implementation.map.postime;

import com.extraleaderboard.business.interfaces.map.postime.TimeBusinessLocal;
import com.extraleaderboard.logic.handler.MainHandler;
import com.extraleaderboard.model.Request;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoLiveServices;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class TimeBusinessImpl implements TimeBusinessLocal {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getTime(String mapId, int position) {
        String finalUrl = generateUrl(mapId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("length", 0);
        parameters.put("offset", position - 1);
        parameters.put("onlyWorld", "true");

        Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, parameters, Request.ResponseType.TIME);

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(Collections.singletonList(request));

        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getTimes(String mapId, List<Integer> positions) {
        String finalUrl = generateUrl(mapId);

        List<Request> requests = new ArrayList<>();

        for (int position : positions) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("length", 0);
            parameters.put("offset", position - 1);
            parameters.put("onlyWorld", "true");

            Request request = new Request(Audience.NADEO_LIVE_SERVICES, finalUrl, parameters, Request.ResponseType.TIME);
            requests.add(request);
        }

        MainHandler mainHandler = new MainHandler();
        Object response = mainHandler.process(requests);

        return response;
    }


    private String generateUrl(String mapId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("mapId", mapId);

        return NadeoLiveServices.buildUrl(NadeoLiveServices.MAP_LEADERBOARD.getUrl(), urlParams);
    }
}
