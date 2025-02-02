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
        this.lvlData = deepCopyArray(lvlData);
    }

    /**
     * Creates a deep copy of a two-dimensional integer array. If the input
     * array is null, an empty 2D array is returned instead of null.
     *
     * @param original the original 2D array to copy.
     * @return a new 2D array that is a deep copy of the input array, or an
     * empty array if the input is null.
     */
    private static int[][] deepCopyArray(final int[][] original) {
        if (original == null) {
            return new int[0][0];
        }
        final int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
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
