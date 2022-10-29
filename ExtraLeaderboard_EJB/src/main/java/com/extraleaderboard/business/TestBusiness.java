package com.extraleaderboard.business;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

import javax.ejb.Local;

@Local
public interface TestBusiness {

    String hello();

    NadeoToken getToken(Audience audience);
}
