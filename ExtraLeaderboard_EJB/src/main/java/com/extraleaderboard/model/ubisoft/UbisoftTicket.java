package com.extraleaderboard.model.ubisoft;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UbisoftTicket implements Serializable {

    @Serial
    private static final long serialVersionUID = -8414733067625911466L;
    @JsonProperty("platformType")
    private String platformType;
    @JsonProperty("ticket")
    private String ticket;
    @JsonProperty("twoFactorAuthenticationTicket")
    private Object twoFactorAuthenticationTicket;
    @JsonProperty("profileId")
    private String profileId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("nameOnPlatform")
    private String nameOnPlatform;
    @JsonProperty("environment")
    private String environment;
    @JsonProperty("expiration")
    private String expiration;
    @JsonProperty("spaceId")
    private String spaceId;
    @JsonProperty("clientIp")
    private String clientIp;
    @JsonProperty("clientIpCountry")
    private String clientIpCountry;
    @JsonProperty("serverTime")
    private String serverTime;
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("sessionKey")
    private String sessionKey;
    @JsonProperty("rememberMeTicket")
    private Object rememberMeTicket;

    @JsonProperty("platformType")
    public String getPlatformType() {
        return platformType;
    }

    @JsonProperty("platformType")
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    @JsonProperty("ticket")
    public String getTicket() {
        return ticket;
    }

    @JsonProperty("ticket")
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @JsonProperty("twoFactorAuthenticationTicket")
    public Object getTwoFactorAuthenticationTicket() {
        return twoFactorAuthenticationTicket;
    }

    @JsonProperty("twoFactorAuthenticationTicket")
    public void setTwoFactorAuthenticationTicket(Object twoFactorAuthenticationTicket) {
        this.twoFactorAuthenticationTicket = twoFactorAuthenticationTicket;
    }

    @JsonProperty("profileId")
    public String getProfileId() {
        return profileId;
    }

    @JsonProperty("profileId")
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("nameOnPlatform")
    public String getNameOnPlatform() {
        return nameOnPlatform;
    }

    @JsonProperty("nameOnPlatform")
    public void setNameOnPlatform(String nameOnPlatform) {
        this.nameOnPlatform = nameOnPlatform;
    }

    @JsonProperty("environment")
    public String getEnvironment() {
        return environment;
    }

    @JsonProperty("environment")
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @JsonProperty("expiration")
    public String getExpiration() {
        return expiration;
    }

    @JsonProperty("expiration")
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @JsonProperty("spaceId")
    public String getSpaceId() {
        return spaceId;
    }

    @JsonProperty("spaceId")
    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    @JsonProperty("clientIp")
    public String getClientIp() {
        return clientIp;
    }

    @JsonProperty("clientIp")
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @JsonProperty("clientIpCountry")
    public String getClientIpCountry() {
        return clientIpCountry;
    }

    @JsonProperty("clientIpCountry")
    public void setClientIpCountry(String clientIpCountry) {
        this.clientIpCountry = clientIpCountry;
    }

    @JsonProperty("serverTime")
    public String getServerTime() {
        return serverTime;
    }

    @JsonProperty("serverTime")
    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("sessionKey")
    public String getSessionKey() {
        return sessionKey;
    }

    @JsonProperty("sessionKey")
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @JsonProperty("rememberMeTicket")
    public Object getRememberMeTicket() {
        return rememberMeTicket;
    }

    @JsonProperty("rememberMeTicket")
    public void setRememberMeTicket(Object rememberMeTicket) {
        this.rememberMeTicket = rememberMeTicket;
    }

    @Override
    public String toString() {
        return "UbisoftTicket{" +
                "platformType='" + platformType + '\'' +
                ", ticket='" + ticket + '\'' +
                ", twoFactorAuthenticationTicket=" + twoFactorAuthenticationTicket +
                ", profileId='" + profileId + '\'' +
                ", userId='" + userId + '\'' +
                ", nameOnPlatform='" + nameOnPlatform + '\'' +
                ", environment='" + environment + '\'' +
                ", expiration='" + expiration + '\'' +
                ", spaceId='" + spaceId + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", clientIpCountry='" + clientIpCountry + '\'' +
                ", serverTime='" + serverTime + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", rememberMeTicket=" + rememberMeTicket +
                '}';
    }
}