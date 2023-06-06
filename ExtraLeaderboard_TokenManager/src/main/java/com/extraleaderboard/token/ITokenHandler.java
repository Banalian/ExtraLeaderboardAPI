package com.extraleaderboard.token;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

/**
 * Interface used to get tokens from the Nadeo API, allows to easily create different behavior for the token handling
 */
public interface ITokenHandler {

    /**
     * Get the {@link NadeoToken} for the given audience
     * @param audience the audience for which the token is needed
     * @return the token, either a new one or an existing one
     */
    NadeoToken getNadeoToken(Audience audience);
}
