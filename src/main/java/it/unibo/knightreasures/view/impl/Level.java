package it.unibo.knightreasures.view.impl;

import java.util.Arrays;

/**
 * Represents a game level containing level data in a 2D array. This class is
 * designed for managing level structures and retrieving sprite indices.
 *
 * <p>
 * Note: If this class is not meant for inheritance, consider making it
 * final.</p>
 */
public class Level {

    private final int[][] lvlData;

    /**
     * Constructs a new Level with the given level data.
     *
     * @param lvlData A 2D integer array representing the level structure.
     */
    public Level(final int[][] lvlData) {
        this.lvlData = lvlData.clone();
    }

    /**
     * Retrieves the sprite index at a given (x, y) coordinate.
     *
     * @param x The x-coordinate in the level grid.
     * @param y The y-coordinate in the level grid.
     * @return The sprite index at the specified position.
     */
    public int getSpriteIndex(final int x, final int y) {
        return lvlData[y][x];
    }

    /**
     * Returns the entire level data as a 2D array.
     *
     * <p>
     * Subclasses should override this method with caution.</p>
     *
     * @return A 2D array representing the level structure.
     */
    public int[][] getLevelData() {
        return Arrays.stream(this.lvlData).map(int[]::clone).toArray(int[][]::new);
    }
}
