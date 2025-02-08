package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Manages all interactive objects within the game, such as chests, treasures, and spikes.
 */
public interface ObjectManager {

    /**
     * Checks if all treasures have been collected.
     *
     * @return The number of collected treasures.
     */
    int isAllCollectedTreasures();

    /**
     * Checks if a chest has been opened.
     *
     * @param hitbox The hitbox of the entity interacting with the chest.
     */
    void checkChestOpened(Rectangle2D.Float hitbox);

    /**
     * Checks if an object (e.g., treasure) has been touched.
     *
     * @param hitbox The hitbox of the entity interacting with the object.
     */
    void checkObjectTouched(Rectangle2D.Float hitbox);

    /**
     * Checks if a spike has been touched, reducing player health if necessary.
     *
     * @param player The player entity.
     * @param hearts The hearts system managing player health.
     */
    void checkSpikeTouched(PlayerEntityImpl player, HeartsImpl hearts);

    /**
     * Draws all objects in the level.
     *
     * @param g The graphics object used for rendering.
     * @param xLvlOffset The level offset for rendering.
     */
    void draw(Graphics g, int xLvlOffset);

    /**
     * Loads objects for a new level.
     *
     * @param newLevel The level to load objects into.
     */
    void loadObjects(LevelImpl newLevel);

    /**
     * Resets all objects to their initial state.
     */
    void resetAllObjects();

    /**
     * Updates the state of all objects.
     */
    void update();
}
