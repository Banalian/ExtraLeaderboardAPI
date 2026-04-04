package com.extraleaderboard.logic.token;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

import java.util.Map;
import java.util.Timer;

/**
 * Factory used to get a token from the Nadeo API, delegating the token handling to the {@link TokenHandler}
 */
public class TokenFactory {

    private static final NadeoServiceTokenHandler TOKEN_HANDLER = new NadeoServiceTokenHandler();

    private TokenFactory() {
    }

    public static NadeoToken getToken(Audience audience) {
        return TOKEN_HANDLER.getNadeoToken(audience);
    }

    public static Map<Audience, Timer> getTimers() {
        return TOKEN_HANDLER.getTimers();
    }
}
