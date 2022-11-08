package com.extraleaderboard.model.nadeo;

public interface INadeoService {

    static String getAudienceName() {
        throw new NoSuchMethodError("Method not implemented");
    }

    Class getResponseClass();

    Audience getAudience();
    
}
