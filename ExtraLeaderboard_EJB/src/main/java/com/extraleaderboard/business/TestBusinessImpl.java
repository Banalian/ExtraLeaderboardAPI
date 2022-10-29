package com.extraleaderboard.business;

import com.extraleaderboard.logic.token.TokenFactory;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;

import javax.ejb.Stateless;

@Stateless
public class TestBusinessImpl implements TestBusiness {

    @Override
    public String hello() {
        return "Hello, World!";
    }

    @Override
    public NadeoToken getToken(Audience audience) {
        return TokenFactory.getToken(audience);
    }
}
