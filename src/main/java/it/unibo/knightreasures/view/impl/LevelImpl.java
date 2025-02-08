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

    private final BufferedImage img;
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
        this.img = img;
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
     * Retrieves the sprite index at a given (x, y) coordinate.
     *
     * @param x The x-coordinate in the level grid.
     * @param y The y-coordinate in the level grid.
     * @return The sprite index at the specified position.
     */
    @Override
    public int getSpriteIndex(final int x, final int y) {
        return lvlData[y][x];
    }

    /**
     * Returns the entire level data as a 2D array.
     *
     * @return A 2D array representing the level structure.
     */
    @Override
    public int[][] getLevelData() {
        return lvlData;
    }

    /**
     * Retrieves the maximum level offset in pixels.
     * This value is used to determine how far the camera can move horizontally.
     *
     * @return the maximum horizontal offset of the level.
     */
    @Override
    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    /**
     * Retrieves a list of skeleton enemies present in the level.
     *
     * @return a list of {@link SkeletonImpl} representing the enemies in the level.
     */
    @Override
    public List<SkeletonImpl> getSkeletons() {
        return skeletons;
    }

    /**
     * Retrieves a list of treasures present in the level.
     *
     * @return a list of {@link TreasureImpl} representing the treasures in the level.
     */
    @Override
    public List<TreasureImpl> getTreasures() {
        return treasures;
    }

    /**
     * Retrieves a list of chests present in the level.
     *
     * @return a list of {@link ChestImpl} representing the chests in the level.
     */
    @Override
    public List<ChestImpl> getChests() {
        return chests;
    }

    /**
     * Retrieves a list of spikes present in the level.
     *
     * @return a list of {@link SpikeImpl} representing the spikes in the level.
     */
    @Override
    public List<SpikeImpl> getSpikes() {
        return spikes;
    }

    /**
     * Retrieves the spawn point of the player in the level.
     *
     * @return a {@link Point} representing the player's spawn coordinates.
     */
    @Override
    public Point getPlayerSpawn() {
        return playerSpawn;
    }
}
