package com.extraleaderboard;

import com.extraleaderboard.logic.token.TokenFactory;
import com.extraleaderboard.model.nadeo.Audience;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class MainApplication extends Application {

    public MainApplication() {
        super();

        // Workaround : Create the needed token
        TokenFactory.getToken(Audience.NADEO_LIVE_SERVICES);
    }
}
