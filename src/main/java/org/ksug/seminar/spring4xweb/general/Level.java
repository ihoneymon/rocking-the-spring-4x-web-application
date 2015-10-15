package org.ksug.seminar.spring4xweb.general;

import lombok.Getter;

@Getter
public enum Level {
    GOLD(3, null), SILVER(2, GOLD), BRONZE(1, SILVER);

    private int value;
    private Level nextLevel;

    /**
     * @param value
     * @param nextLevel
     */
    private Level(int value, Level nextLevel) {
        this.value = value;
        this.nextLevel = nextLevel;
    }

    public static Level valueOf(int value) {
        for (Level level : values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new AssertionError("Unknown value: " + value);
    }
}
