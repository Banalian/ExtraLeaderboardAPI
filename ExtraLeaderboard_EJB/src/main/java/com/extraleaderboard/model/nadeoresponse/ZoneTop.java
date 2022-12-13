package com.extraleaderboard.model.nadeoresponse;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZoneTop {

    @JsonProperty("zoneId")
    private String zoneId;
    @JsonProperty("zoneName")
    private String zoneName;
    @JsonProperty("top")
    private List<Top> top = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("zoneId")
    public String getZoneId() {
        return zoneId;
    }

    @JsonProperty("zoneId")
    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    @JsonProperty("zoneName")
    public String getZoneName() {
        return zoneName;
    }

    @JsonProperty("zoneName")
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @JsonProperty("top")
    public List<Top> getTop() {
        return top;
    }

    @JsonProperty("top")
    public void setTop(List<Top> top) {
        this.top = top;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}