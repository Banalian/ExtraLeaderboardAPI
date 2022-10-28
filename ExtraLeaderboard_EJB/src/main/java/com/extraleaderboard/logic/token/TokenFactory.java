package com.extraleaderboard.logic.token;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

/**
 * Factory used to get a token from the Nadeo API, delegating the token handling to the {@link TokenHandler}
 */
public class TokenFactory {

    private static final TokenHandler TOKEN_HANDLER = new TokenHandler();

    private TokenFactory() {
    }

    public static NadeoToken getToken(Audience audience) {
        return TOKEN_HANDLER.getNadeoToken(audience);
    }
}
