package it.unibo.knightreasures.view.impl;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import it.unibo.knightreasures.model.impl.ChestImpl;
import it.unibo.knightreasures.model.impl.Skeleton;
import it.unibo.knightreasures.model.impl.Spike;
import it.unibo.knightreasures.model.impl.Treasure;
import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Represents a game level containing level data in a 2D array. This class is
 * designed for managing level structures and retrieving sprite indices.
 */
public class Level {

    private final BufferedImage img;
    private List<Skeleton> skeletons;
    private List<ChestImpl> chests;
    private List<Treasure> treasures;
    private List<Spike> spikes;
    private int [][] lvlData;
    private int lvlTilesWide, maxLvlOffsetX, maxTilesOffset;
    private Point playerSpawn;

    /**
     * Constructs a new Level with the given level data.
     *
     * @param img the image of the level.
     */
    public Level(final BufferedImage img) {
        this.img = img;
        createLvlData();
        createEnemies();
        createSpikes();
        createTreasure();
        createChests();
        calculateOffset();
        calculatePlayerSpawn();
    }

    private void calculatePlayerSpawn() {
        playerSpawn = HelpMethods.getPlayerSpawn(img);
    }

    private void createSpikes() {
        spikes = HelpMethods.getSpike(img);
    }

    private void createTreasure() {
        treasures = HelpMethods.getTreasure(img);
    }

    private void createChests() {
        chests = HelpMethods.getChest(img);
    }

    private void createEnemies() {
        skeletons = HelpMethods.getSkeletons(img);
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

    private void createLvlData() {
        lvlData = HelpMethods.createLevel(img);
    }

    private void calculateOffset() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Window.TILES_IN_WIDTH;
        maxLvlOffsetX = Window.TILES_SIZE * maxTilesOffset;
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
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public List<Skeleton> getSkeletons() {
        return skeletons;
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public List<ChestImpl> getChests() {
        return chests;
    }

    public List<Spike> getSpikes() {
        return spikes;
    }

    public Point getPlayerSpawn() {
        return this.playerSpawn;
    }
}