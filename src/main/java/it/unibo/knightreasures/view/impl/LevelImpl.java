package it.unibo.knightreasures.view.impl;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import it.unibo.knightreasures.model.impl.ChestImpl;
import it.unibo.knightreasures.model.impl.SkeletonImpl;
import it.unibo.knightreasures.model.impl.SpikeImpl;
import it.unibo.knightreasures.model.impl.TreasureImpl;
import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.Level;

/**
 * Represents a game level containing level data in a 2D array.
 * This class is responsible for managing level structures and retrieving sprite indices.
 */
public final class LevelImpl implements Level {

    private final List<SkeletonImpl> skeletons;
    private final List<ChestImpl> chests;
    private final List<TreasureImpl> treasures;
    private final List<SpikeImpl> spikes;
    private final int[][] lvlData;
    private final int lvlTilesWide;
    private final int maxLvlOffsetX;
    private final int maxTilesOffset;
    private final Point playerSpawn;

    /**
     * Constructs a new Level with the given level image.
     *
     * @param img the BufferedImage representing the level.
     */
    public LevelImpl(final BufferedImage img) {
        this.lvlData = HelpMethods.createLevel(img);
        this.skeletons = HelpMethods.getSkeletons(img);
        this.spikes = HelpMethods.getSpike(img);
        this.treasures = HelpMethods.getTreasure(img);
        this.chests = HelpMethods.getChest(img);
        this.playerSpawn = HelpMethods.getPlayerSpawn(img);

        this.lvlTilesWide = img.getWidth();
        this.maxTilesOffset = lvlTilesWide - Window.TILES_IN_WIDTH;
        this.maxLvlOffsetX = Window.TILES_SIZE * maxTilesOffset;
    }

    /**
     * Retrieves the sprite index at a specific (x, y) position in the level.
     *
     * @param x The x-coordinate in the level grid.
     * @param y The y-coordinate in the level grid.
     * @return The sprite index at the given position.
     */
    @Override
    public int getSpriteIndex(final int x, final int y) {
        return lvlData[y][x];
    }

    /**
     * Returns a copy of the level data to prevent external modifications.
     *
     * @return A copy of the 2D level data array.
     */
    @Override
    public int[][] getLevelData() {
        final int[][] copy = new int[lvlData.length][];
        for (int i = 0; i < lvlData.length; i++) {
            copy[i] = lvlData[i].clone();
        }
        return copy;
    }

    /**
     * Gets the maximum level offset for scrolling.
     *
     * @return The max level offset.
     */
    @Override
    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    /**
     * Retrieves a list of skeleton enemies in the level.
     *
     * @return A list of skeletons.
     */
    @Override
    public List<SkeletonImpl> getSkeletons() {
        return skeletons;
    }

    /**
     * Retrieves a list of treasures present in the level.
     *
     * @return A list of treasures.
     */
    @Override
    public List<TreasureImpl> getTreasures() {
        return treasures;
    }

    /**
     * Retrieves a list of chests present in the level.
     *
     * @return A list of chests.
     */
    @Override
    public List<ChestImpl> getChests() {
        return chests;
    }

    /**
     * Retrieves a list of spikes present in the level.
     *
     * @return A list of spikes.
     */
    @Override
    public List<SpikeImpl> getSpikes() {
        return spikes;
    }

    /**
     * Retrieves the player's spawn point in the level.
     *
     * @return The player's spawn point as a {@code Point} object.
     */
    @Override
    public Point getPlayerSpawn() {
        return playerSpawn;
    }
}
