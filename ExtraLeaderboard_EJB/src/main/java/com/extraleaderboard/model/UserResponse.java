package com.extraleaderboard.model;

import java.util.List;
import java.util.Map;

public class UserResponse implements Cloneable{
    private List<LeaderboardPosition> positions;
    private Map<String, Object> meta;

    private MapInfo mapInfo;

    public List<LeaderboardPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<LeaderboardPosition> positions) {
        this.positions = positions;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
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
