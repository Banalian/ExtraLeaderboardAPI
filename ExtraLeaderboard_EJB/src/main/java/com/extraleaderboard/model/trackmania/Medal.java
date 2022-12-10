package com.extraleaderboard.model.trackmania;

import java.util.Arrays;

/**
 * Enum representing the medals available in Trackmania
 */
public enum Medal {

    /**
     * No medal
     */
    NONE(0),

    /**
     * Bronze medal
     */
    BRONZE(1),

    /**
     * Silver medal
     */
    SILVER(2),

    /**
     * Gold medal
     */
    GOLD(3),

    /**
     * Author medal
     */
    AUTHOR(4);

    /**
     * The value of the medal
     */
    private final int value;

    /**
     * Constructor
     *
     * @param value the value of the medal
     */
    Medal(int value) {
        this.value = value;
    }

    /**
     * Get the medal from the value
     *
     * @param value the value of the medal
     * @return the medal
     */
    public static Medal getMedalFromValue(int value) {
        return Arrays.stream(Medal.values())
                .filter(medal -> medal.getValue() == value)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get the value of the medal
     *
     * @return the value of the medal
     */
    public int getValue() {
        return value;
    }

}
