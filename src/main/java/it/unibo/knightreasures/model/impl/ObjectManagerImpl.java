package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.api.ObjectManager;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;
import it.unibo.knightreasures.view.impl.LevelManagerImpl;

/**
 * Manages the objects in the game, including treasures, chests, and spikes.
 * Handles their loading, updating, rendering, and interactions with the player.
 */
public class ObjectManagerImpl implements ObjectManager {

    private final GameplayImpl playing;
    private final LevelManagerImpl level;
    private BufferedImage[][] treasureImgs, chestImgs;
    private BufferedImage spikeImgs;
    private List<TreasureImpl> treasures = new ArrayList<>();
    private List<ChestImpl> chests = new ArrayList<>();
    private List<SpikeImpl> spikes = new ArrayList<>();
    private int collectedTreasure = ObjectsValues.INITIAL_COLLECTED_TREASURES;

    /**
     * Constructs an ObjectManager to manage the level's objects.
     *
     * @param playing The gameplay instance that manages the game state.
     * @param level   The level manager that contains level data.
     */
    public ObjectManagerImpl(final GameplayImpl playing, final LevelManagerImpl level) {
        this.playing = playing;
        this.level = level;
        loadImgs();
    }

    /**
     * Loads images for all game objects, including treasures, chests, and spikes.
     */
    private void loadImgs() {
        BufferedImage treasureImg = ResourceFuncUtilities.loadSources(Images.TREASURE);
        treasureImgs = new BufferedImage[ObjectsValues.TREASURE_SPRITES_ROWS][ObjectsValues.TREASURE_SPRITES_COLUMNS];

        for (int j = 0; j < treasureImgs.length; j++) {
            for (int i = 0; i < treasureImgs[j].length; i++) {
                treasureImgs[j][i] = treasureImg.getSubimage(
                        ObjectConstants.TREASURE_WIDTH_DEFAULT * i,
                        ObjectConstants.TREASURE_HEIGHT_DEFAULT * j,
                        ObjectConstants.TREASURE_WIDTH_DEFAULT,
                        ObjectConstants.TREASURE_HEIGHT_DEFAULT);
            }
        }

        BufferedImage chestImg = ResourceFuncUtilities.loadSources(Images.CHEST);
        chestImgs = new BufferedImage[ObjectsValues.CHEST_SPRITES_ROWS][ObjectsValues.CHEST_SPRITES_COLUMNS];

        for (int j = 0; j < chestImgs.length; j++) {
            for (int i = 0; i < chestImgs[j].length; i++) {
                chestImgs[j][i] = chestImg.getSubimage(
                        ObjectConstants.CHEST_WIDTH_DEFAULT * i,
                        ObjectConstants.CHEST_HEIGHT_DEFAULT * j,
                        ObjectConstants.CHEST_WIDTH_DEFAULT,
                        ObjectConstants.CHEST_HEIGHT_DEFAULT);
            }
        }

        spikeImgs = ResourceFuncUtilities.loadSources(Images.SPIKE);
    }

    /**
     * Draws chests on the screen.
     *
     * @param g          The graphics context used for rendering.
     * @param xLvlOffset The level's horizontal offset.
     */
    private void drawChests(final Graphics g, final int xLvlOffset) {
        for (ChestImpl c : chests) {
            if (c.isActive()) {
                g.drawImage(chestImgs[ObjectsValues.CHEST_IMAGES_INDEX][c.getAniIndex()],
                        (int) (c.getHitbox().x - c.getXOffset() - ObjectConstants.CHEST_X_SHIFT - xLvlOffset),
                        (int) (c.getHitbox().y - c.getYOffset() - ObjectConstants.CHEST_Y_SHIFT),
                        ObjectConstants.CHEST_WIDTH,
                        ObjectConstants.CHEST_HEIGHT,
                        null);
            }
        }
    }

    /**
     * Draws treasures on the screen.
     *
     * @param g          The graphics context used for rendering.
     * @param xLvlOffset The level's horizontal offset.
     */
    private void drawTreasures(final Graphics g, final int xLvlOffset) {
        for (TreasureImpl t : treasures) {
            if (t.isActive()) {
                g.drawImage(treasureImgs[t.getObjType()][t.getAniIndex()],
                        (int) (t.getHitbox().x - ObjectConstants.TREASURE_X_OFFSET - xLvlOffset),
                        (int) (t.getHitbox().y - ObjectConstants.TREASURE_Y_OFFSET),
                        ObjectConstants.TREASURE_WIDTH,
                        ObjectConstants.TREASURE_HEIGHT,
                        null);
            }
        }
    }

    /**
     * Draws spikes on the screen.
     *
     * @param g          The graphics context used for rendering.
     * @param xLvlOffset The level's horizontal offset.
     */
    private void drawSpikes(final Graphics g, final int xLvlOffset) {
        for (SpikeImpl s : spikes) {
            g.drawImage(spikeImgs,
                    (int) (s.getHitbox().x - xLvlOffset),
                    (int) (s.getHitbox().y - s.getYOffset()),
                    ObjectConstants.SPIKE_WIDTH,
                    ObjectConstants.SPIKE_HEIGHT,
                    null);
        }
    }

    /**
     * Updates all game objects.
     */
    @Override
    public void update() {
        for (TreasureImpl t : treasures) {
            if (t.isActive()) {
                t.update();
            }
        }

        for (ChestImpl c : chests) {
            if (c.isActive()) {
                c.update();
            }
        }
    }

    /**
     * Draws all objects in the game.
     *
     * @param g          The graphics context used for rendering.
     * @param xLvlOffset The level's horizontal offset.
     */
    @Override
    public void draw(final Graphics g, final int xLvlOffset) {
        drawTreasures(g, xLvlOffset);
        drawChests(g, xLvlOffset);
        drawSpikes(g, xLvlOffset);
    }

    /**
     * Checks if the player has touched a spike and reduces health accordingly.
     *
     * @param player The player entity.
     * @param hearts The hearts UI managing the player's health.
     */
    @Override
    public void checkSpikeTouched(final PlayerEntityImpl player, final HeartsImpl hearts) {
        for (SpikeImpl spike : spikes) {
            if (spike.getHitbox().intersects(player.getHitbox())) {
                hearts.setCurrentHearts(player.getCurrentHealth() - ObjectsValues.SPIKE_DAMAGE);
            }
        }
    }

    /**
     * Checks if the player has collected a treasure.
     *
     * @param hitbox The player's hitbox.
     */
    @Override
    public void checkObjectTouched(final Rectangle2D.Float hitbox) {
        for (TreasureImpl t : treasures) {
            if (t.isActive() && hitbox.intersects(t.getHitbox())) {
                t.setActive(false);
                collectedTreasure++;
            }
        }
    }

    /**
     * Checks if the player has opened a chest and spawns a treasure if so.
     *
     * @param hitbox The player's hitbox.
     */
    @Override
    public void checkChestOpened(final Rectangle2D.Float hitbox) {
        for (ChestImpl c : chests) {
            if (c.isActive() && !c.isOpened() && c.getHitbox().intersects(hitbox)) {
                c.setAnimation(true);
                c.setOpened(true);
                treasures.add(new TreasureImpl(
                        (int) (c.getHitbox().x + ObjectConstants.TREASURE_X_SHIFT),
                        (int) (c.getHitbox().y - ObjectConstants.TREASURE_Y_SHIFT),
                        ObjectsValues.LEVEL_TREASURES[level.getLevelIndex()]
                ));
                return;
            }
        }
    }

    /**
     * Loads objects for a new level.
     *
     * @param newLevel The new level instance.
     */
    @Override
    public void loadObjects(final LevelImpl newLevel) {
        treasures = new ArrayList<>(newLevel.getTreasures());
        chests = new ArrayList<>(newLevel.getChests());
        spikes = newLevel.getSpikes();
    }

    /**
     * Resets all objects to their initial state.
     */
    @Override
    public void resetAllObjects() {
        loadObjects(playing.getLevel().getCurrentLevel());
        for (TreasureImpl t : treasures) {
            t.reset();
        }

        for (ChestImpl c : chests) {
            c.reset();
        }
        collectedTreasure = ObjectsValues.INITIAL_COLLECTED_TREASURES;
    }

    /**
     * Returns the number of collected treasures.
     *
     * @return The count of collected treasures.
     */
    @Override
    public int isAllCollectedTreasures() {
        return collectedTreasure;
    }
}
