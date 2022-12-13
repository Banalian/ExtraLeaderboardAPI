package com.extraleaderboard.model.nadeo;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Audience {

    NADEO_SERVICES("NadeoServices"),
    NADEO_LIVE_SERVICES("NadeoLiveServices"),
    NADEO_CLUB_SERVICES("NadeoClubServices");

    @JsonProperty("audience")
    private final String audienceName;

    Audience(String audience) {
        this.audienceName = audience;
    }

    public String getAudienceName() {
        return audienceName;
    }

    @Override
    public String toString() {
        return "Audience{" +
                "audience='" + audienceName + '\'' +
                '}';
    }
}
