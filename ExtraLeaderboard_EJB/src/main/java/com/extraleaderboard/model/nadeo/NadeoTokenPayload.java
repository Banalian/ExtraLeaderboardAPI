package com.extraleaderboard.model.nadeo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NadeoTokenPayload implements Serializable {

    @Serial
    private static final long serialVersionUID = -5805324569748084310L;

    @JsonProperty("jti")
    private String jti;
    @JsonProperty("iss")
    private String iss;
    @JsonProperty("iat")
    private Integer iat;

    /**
     * Time after which you can refresh the token
     */
    @JsonProperty("rat")
    private Integer rat;

    /**
     * Expiration time of the token
     */
    @JsonProperty("exp")
    private Integer exp;
    @JsonProperty("aud")
    private String aud;
    @JsonProperty("usg")
    private String usg;
    @JsonProperty("sid")
    private String sid;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("aun")
    private String aun;
    @JsonProperty("rtk")
    private Boolean rtk;
    @JsonProperty("pce")
    private Boolean pce;

    @JsonProperty("jti")
    public String getJti() {
        return jti;
    }

    @JsonProperty("jti")
    public void setJti(String jti) {
        this.jti = jti;
    }

    @JsonProperty("iss")
    public String getIss() {
        return iss;
    }

    @JsonProperty("iss")
    public void setIss(String iss) {
        this.iss = iss;
    }

    @JsonProperty("iat")
    public Integer getIat() {
        return iat;
    }

    @JsonProperty("iat")
    public void setIat(Integer iat) {
        this.iat = iat;
    }

    @JsonProperty("rat")
    public Integer getRat() {
        return rat;
    }

    @JsonProperty("rat")
    public void setRat(Integer rat) {
        this.rat = rat;
    }

    @JsonProperty("exp")
    public Integer getExp() {
        return exp;
    }

    @JsonProperty("exp")
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @JsonProperty("aud")
    public String getAud() {
        return aud;
    }

    @JsonProperty("aud")
    public void setAud(String aud) {
        this.aud = aud;
    }

    @JsonProperty("usg")
    public String getUsg() {
        return usg;
    }

    @JsonProperty("usg")
    public void setUsg(String usg) {
        this.usg = usg;
    }

    @JsonProperty("sid")
    public String getSid() {
        return sid;
    }

    @JsonProperty("sid")
    public void setSid(String sid) {
        this.sid = sid;
    }

    @JsonProperty("sub")
    public String getSub() {
        return sub;
    }

    @JsonProperty("sub")
    public void setSub(String sub) {
        this.sub = sub;
    }

    @JsonProperty("aun")
    public String getAun() {
        return aun;
    }

    @JsonProperty("aun")
    public void setAun(String aun) {
        this.aun = aun;
    }

    @JsonProperty("rtk")
    public Boolean getRtk() {
        return rtk;
    }

    @JsonProperty("rtk")
    public void setRtk(Boolean rtk) {
        this.rtk = rtk;
    }

    @JsonProperty("pce")
    public Boolean getPce() {
        return pce;
    }

    @JsonProperty("pce")
    public void setPce(Boolean pce) {
        this.pce = pce;
    }

}