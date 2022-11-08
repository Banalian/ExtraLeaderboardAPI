package com.extraleaderboard.model.nadeo;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Audience {

    NADEO_SERVICES("NadeoServices"),
    NADEO_LIVE_SERVICES("NadeoLiveServices"),
    NADEO_CLUB_SERVICES("NadeoClubServices");

    @JsonProperty("audience")
    private final String audience;

    Audience(String audience) {
        this.audience = audience;
    }

    public String getAudience() {
        return audience;
    }

    @Override
    public String toString() {
        return "Audience{" +
                "audience='" + audience + '\'' +
                '}';
    }
}
