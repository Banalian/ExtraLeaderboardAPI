package com.extraleaderboard.token;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

import java.util.EnumMap;
import java.util.Map;
import java.util.Timer;

/**
 * Factory used to get a token from the Nadeo API, delegating the token handling to the {@link ITokenHandler}
 */
public class TokenFactory {

    private static final ITokenHandler TOKEN_HANDLER = new TokenHandler();

    private TokenFactory() {
    }

    public static NadeoToken getToken(Audience audience) {
        return TOKEN_HANDLER.getNadeoToken(audience);
    }

    public static Map<Audience, Timer> getTimers() {
        // try to cast it to a TokenHandler to get the timers
        if(TOKEN_HANDLER instanceof TokenHandler handler){
            return handler.getTimers();
        }
        return new EnumMap<>(Audience.class);
    }
}
