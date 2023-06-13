package com.extraleaderboard;

import com.extraleaderboard.token.TokenFactory;
import com.extraleaderboard.model.nadeo.Audience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/token")
public class MainApplication extends Application {

    private static Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);
    public MainApplication() {
        super();

        try{
            // Request the token when the application is deployed
            TokenFactory.getToken(Audience.NADEO_LIVE_SERVICES);
        }catch (Exception e){
            LOGGER.error("Error while requesting token", e);
        }
    }
}
