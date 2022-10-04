package com.extraleaderboard.business;

import com.extraleaderboard.model.nadeo.NadeoToken;

import javax.ejb.Stateless;

@Stateless
public class TestBusinessImpl implements TestBusiness {

    @Override
    public String hello() {
        return "Hello, World!";
    }

    @Override
    public NadeoToken getToken() {
        NadeoToken token = new NadeoToken();
        token.setAccessToken("test");
        return token;
    }
}
