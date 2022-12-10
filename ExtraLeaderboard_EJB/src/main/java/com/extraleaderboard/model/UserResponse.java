package com.extraleaderboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResponse implements Cloneable{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LeaderboardPosition> positions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapInfo mapInfo;

    public List<LeaderboardPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<LeaderboardPosition> positions) {
        this.positions = positions;
    }

    public void addPosition(LeaderboardPosition position) {
        if(positions == null) {
            positions = new ArrayList<>();
        }
        this.positions.add(position);
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public void addMeta(String key, Object value) {
        if(meta == null) {
            meta = new HashMap<>();
        }
        this.meta.put(key, value);
    }

    public MapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(MapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }

    /**
     * UserResponse cloning method
     * @return a clone of the current UserResponse
     */
    @Override
    public UserResponse clone() {
        try {
            UserResponse clone = (UserResponse) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
