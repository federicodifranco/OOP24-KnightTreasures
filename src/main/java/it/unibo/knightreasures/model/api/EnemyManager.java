package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Manages all enemies in the game, handling their rendering, updating, 
 * and interactions with the player.
 */
public interface EnemyManager {

    /**
     * Checks if there are any active enemies in the level.
     *
     * @return True if there are active enemies, false otherwise.
     */
    boolean hasActiveEnemies();

    /**
     * Adds enemies to the level based on the given level data.
     *
     * @param level The level where enemies should be added.
     */
    void addEnemies(LevelImpl level);

    /**
     * Checks if an enemy has been hit by the player's attack.
     *
     * @param attackBox The hitbox of the player's attack.
     */
    void checkEnemyHit(Rectangle2D.Float attackBox);

    /**
     * Draws all enemies on the screen.
     *
     * @param g The graphics object used for rendering.
     * @param xLvlOffset The horizontal offset for level scrolling.
     */
    void draw(Graphics g, int xLvlOffset);

    /**
     * Draws only skeleton-type enemies on the screen.
     *
     * @param g The graphics object used for rendering.
     * @param xLvlOffset The horizontal offset for level scrolling.
     */
    void drawSkeletons(Graphics g, int xLvlOffset);

    /**
     * Resets all enemies to their initial states.
     */
    void resetAllEnemies();

    /**
     * Updates the enemies based on the level data and player interactions.
     *
     * @param lvlData The level data used for collision detection.
     * @param player The player entity.
     */
    void update(int[][] lvlData, PlayerEntityImpl player);
}
