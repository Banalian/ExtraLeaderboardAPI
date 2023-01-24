package com.extraleaderboard.model.trackmania;

/**
 * Enum to represent the different types of requests. Used by the plugin to more easily determined which results comes from which request.
 */
public enum EntryType {
    POSITION(0),
    TIME(1),
    PB(2),
    MEDAL(3),
    OTHER(4);

    private final int entryType;

    EntryType(int entryType) {
        this.entryType = entryType;
    }

    public int getEntryType() {
        return entryType;
    }
}
