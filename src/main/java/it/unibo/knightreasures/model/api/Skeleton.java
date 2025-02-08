package it.unibo.knightreasures.model.api;

import it.unibo.knightreasures.model.impl.PlayerEntityImpl;

/**
 * Represents the skeleton enemy behavior in the game.
 */
public interface Skeleton {

    /**
     * Retrieves the X-axis flip value for rendering.
     *
     * @return The flip value on the X-axis.
     */
    int flipX();

    /**
     * Retrieves the width flip value for rendering.
     *
     * @return The width flip value.
     */
    int flipW();

    /**
     * Updates the skeleton's state, including movement and interactions.
     *
     * @param lvlData The 2D array representing the level structure.
     * @param player The player entity interacting with the skeleton.
     */
    void update(int[][] lvlData, PlayerEntityImpl player);

    /**
     * Initializes the attack box and the hitbox for the skeleton.
     */
    void initialize();
}
