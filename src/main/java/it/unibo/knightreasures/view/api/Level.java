package it.unibo.knightreasures.view.api;

import java.awt.Point;
import java.util.List;

import it.unibo.knightreasures.model.impl.ChestImpl;
import it.unibo.knightreasures.model.impl.SkeletonImpl;
import it.unibo.knightreasures.model.impl.SpikeImpl;
import it.unibo.knightreasures.model.impl.TreasureImpl;

/**
 * Represents a game level, containing all the elements present in it,
 * such as enemies, chests, treasures, and spikes.
 */
public interface Level {

    /**
     * Retrieves the list of treasures in the level.
     * 
     * @return a list of {@link TreasureImpl} objects.
     */
    List<TreasureImpl> getTreasures();

    /**
     * Retrieves the list of chests in the level.
     * 
     * @return a list of {@link ChestImpl} objects.
     */
    List<ChestImpl> getChests();

    /**
     * Retrieves the list of spikes in the level.
     * 
     * @return a list of {@link SpikeImpl} objects.
     */
    List<SpikeImpl> getSpikes();

    /**
     * Gets the sprite index at the given coordinates in the level.
     * 
     * @param x the x-coordinate in the level grid.
     * @param y the y-coordinate in the level grid.
     * @return the sprite index at the specified position.
     */
    int getSpriteIndex(int x, int y);

    /**
     * Retrieves the entire level data as a 2D array.
     * 
     * @return a 2D array representing the level structure.
     */
    int[][] getLevelData();

    /**
     * Gets the horizontal offset for rendering the level.
     * 
     * @return the level offset value.
     */
    int getLvlOffset();

    /**
     * Retrieves the list of skeleton enemies present in the level.
     * 
     * @return a list of {@link SkeletonImpl} objects.
     */
    List<SkeletonImpl> getSkeletons();

    /**
     * Gets the player's spawn position in the level.
     * 
     * @return a {@link Point} representing the player's initial position.
     */
    Point getPlayerSpawn();
}
