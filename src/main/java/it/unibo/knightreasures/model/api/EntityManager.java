package it.unibo.knightreasures.model.api;

import java.awt.geom.Rectangle2D;

/**
 * Defines the basic properties and methods for an entity in the game.
 */
public interface EntityManager {

    /**
     * Retrieves the current health of the entity.
     *
     * @return The entity's current health.
     */
    int getCurrentHealth();

    /**
     * Retrieves the current state of the entity.
     *
     * @return The entity's state.
     */
    int getState();

    /**
     * Retrieves the hitbox of the entity for collision detection.
     *
     * @return The hitbox as a Rectangle2D.Float.
     */
    Rectangle2D.Float getHitbox();
}
