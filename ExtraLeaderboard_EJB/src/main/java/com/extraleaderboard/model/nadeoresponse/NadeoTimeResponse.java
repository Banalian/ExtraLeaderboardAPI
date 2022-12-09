package com.extraleaderboard.model.nadeoresponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NadeoTimeResponse extends NadeoResponse{

    @JsonProperty("groupUid")
    private String groupUid;
    @JsonProperty("mapUid")
    private String mapUid;
    @JsonProperty("tops")
    private List<ZoneTop> zoneTops = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("groupUid")
    public String getGroupUid() {
        return groupUid;
    }

    @JsonProperty("groupUid")
    public void setGroupUid(String groupUid) {
        this.groupUid = groupUid;
    }

    @JsonProperty("mapUid")
    public String getMapUid() {
        return mapUid;
    }

    @JsonProperty("mapUid")
    public void setMapUid(String mapUid) {
        this.mapUid = mapUid;
    }

    @JsonProperty("tops")
    public List<ZoneTop> getTops() {
        return zoneTops;
    }

    @JsonProperty("tops")
    public void setTops(List<ZoneTop> zoneTops) {
        this.zoneTops = zoneTops;
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