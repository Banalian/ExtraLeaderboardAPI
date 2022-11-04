package com.extraleaderboard.model;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

import java.util.EnumMap;

/**
 * Class used to store the tokens
 */
public class TokenStorage {

    /**
     * Token map stored by audience
     */
    private static final EnumMap<Audience, NadeoToken> tokens = new EnumMap<>(Audience.class);

    private TokenStorage() {
    }

    public static NadeoToken getToken(Audience audience) {
        return tokens.get(audience);
    }

    public static void setToken(Audience audience, NadeoToken token) {
        tokens.put(audience, token);
    }

    public static void removeToken(Audience audience) {
        tokens.remove(audience);
    }

    public static boolean hasToken(Audience audience) {
        return tokens.containsKey(audience);
    }
}
