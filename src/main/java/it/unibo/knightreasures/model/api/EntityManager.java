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

    /**
     * Gets the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    Rectangle2D.Float getAttackbox();

    /**
     * Sets the player's health.
     *
     * @param currentHealth the current player's health.
     */
    void setCurrentHealth(int currentHealth);

    /**
     * Gets the player's max health.
     *
     * @return the player's max health.
     */
    int getMaxHealth();

    /**
     * Sets the player's max health.
     *
     * @param maxHealth the max player's health.
     */
    void setMaxHealth(int maxHealth);

    /**
     * Gets the X coordinate of the entity.
     *
     * @return the X coordinate.
     */
    float getX();

    /**
     * Sets the X coordinate of the entity.
     *
     * @param x the new X coordinate.
     */
    void setX(float x);

    /**
     * Gets the Y coordinate of the entity.
     *
     * @return the Y coordinate.
     */
    float getY();

    /**
     * Sets the Y coordinate of the entity.
     *
     * @param y the new Y coordinate.
     */
    void setY(float y);

    /**
     * Gets the width of the entity.
     *
     * @return the width of the entity.
     */
    int getWidth();

    /**
     * Sets the width of the entity.
     *
     * @param width the new width.
     */
    void setWidth(int width);

    /**
     * Gets the height of the entity.
     *
     * @return the height of the entity.
     */
    int getHeight();

    /**
     * Sets the height of the entity.
     *
     * @param height the new height.
     */
    void setHeight(int height);

    /**
     * Gets the current animation index.
     *
     * @return the animation index.
     */
    int getAniIndex();
    /**
     * Gets the current animation tick.
     *
     * @return the animation tick.
     */
    int getTick();

    /**
     * Checks if the entity is in the air.
     *
     * @return true if the entity is in the air, false otherwise.
     */
    boolean isInAir();

    /**
     * Gets the air speed of the entity.
     *
     * @return the air speed.
     */
    float getAirSpeed();

    /**
     * Sets whether the entity is in the air.
     *
     * @param inAir true if the entity is in the air, false otherwise.
     */
    void setInAir(boolean inAir);

    /**
     * Sets the air speed of the entity.
     *
     * @param airSpeed the new air speed.
     */
    void setAirSpeed(float airSpeed);

    /**
     * Sets the state of the entity.
     *
     * @param state the new state.
     */
    void setState(int state);

    /**
     * Sets the animation index.
     *
     * @param aniIndex the new animation index.
     */
    void setAniIndex(int aniIndex);

    /**
     * Sets the animation tick.
     *
     * @param aniTick the new animation tick.
     */
    void setAniTick(int aniTick);

    /**
     * Increments the animation index.
     */
    void incrementAniIndex();

    /**
     * Increments the animation tick.
     */
    void incrementAniTick();
}
