package it.unibo.knightreasures.model.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.api.EntityManager;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Represents a generic entity in the game, providing common functionality for
 * position, dimensions, and hitbox management.
 */
public abstract class EntityManagerImpl implements EntityManager {

    private float x, y;
    private int width, height;
    private int currentHealth, maxHealth, aniIndex, aniTick, state;
    private Rectangle2D.Float hitBox, attackBox;
    private boolean inAir;
    private float airSpeed;

    /**
     * Constructs an EntityManager instance.
     *
     * @param x      the X coordinate of the entity.
     * @param y      the Y coordinate of the entity.
     * @param width  the width of the entity.
     * @param height the height of the entity.
     */
    public EntityManagerImpl(final float x, final float y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Abstract method to update the entity's state. All subclasses must
     * implement this method.
     */
    public abstract void update();

    /**
     * Initializes the hitbox of the entity.
     * @param width the width of the hitbox.
     * @param height the height of the hitbox.
     */
    protected void initHitBox(final int width, final int height) {
        this.hitBox = new Rectangle2D.Float(x, y, (int) (width * Window.SCALE), (int) (height * Window.SCALE));
    }

    /**
     * Gets the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    @Override
    public Rectangle2D.Float getHitbox() {
        return this.hitBox;
    }

    /**
     * Gets the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    @Override
    public Rectangle2D.Float getAttackbox() {
        return this.attackBox;
    }

    /**
     * Gets the player's health.
     *
     * @return the current player's health.
     */
    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the player's health.
     *
     * @param currentHealth the current player's health.
     */
    @Override
    public void setCurrentHealth(final int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Gets the player's max health.
     *
     * @return the player's max health.
     */
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the player's max health.
     *
     * @param maxHealth the max player's health.
     */
    @Override
    public void setMaxHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Gets the X coordinate of the entity.
     *
     * @return the X coordinate.
     */
    @Override
    public float getX() {
        return this.x;
    }

    /**
     * Sets the X coordinate of the entity.
     *
     * @param x the new X coordinate.
     */
    @Override
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate of the entity.
     *
     * @return the Y coordinate.
     */
    @Override
    public float getY() {
        return this.y;
    }

    /**
     * Sets the Y coordinate of the entity.
     *
     * @param y the new Y coordinate.
     */
    @Override
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Gets the width of the entity.
     *
     * @return the width of the entity.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the width of the entity.
     *
     * @param width the new width.
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Gets the height of the entity.
     *
     * @return the height of the entity.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the height of the entity.
     *
     * @param height the new height.
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Gets the current state of the entity.
     *
     * @return the current state.
     */
    @Override
    public int getState() {
        return this.state;
    }

    /**
     * Gets the current animation index.
     *
     * @return the animation index.
     */
    @Override
    public int getAniIndex() {
        return this.aniIndex;
    }

    /**
     * Gets the current animation tick.
     *
     * @return the animation tick.
     */
    @Override
    public int getTick() {
        return this.aniTick;
    }

    /**
     * Checks if the entity is in the air.
     *
     * @return true if the entity is in the air, false otherwise.
     */
    @Override
    public boolean isInAir() {
        return this.inAir;
    }

    /**
     * Gets the air speed of the entity.
     *
     * @return the air speed.
     */
    @Override
    public float getAirSpeed() {
        return this.airSpeed;
    }

    /**
     * Sets whether the entity is in the air.
     *
     * @param inAir true if the entity is in the air, false otherwise.
     */
    @Override
    public void setInAir(final boolean inAir) {
        this.inAir = inAir;
    }

    /**
     * Sets the air speed of the entity.
     *
     * @param airSpeed the new air speed.
     */
    @Override
    public void setAirSpeed(final float airSpeed) {
        this.airSpeed = airSpeed;
    }

    /**
     * Sets the state of the entity.
     *
     * @param state the new state.
     */
    @Override
    public void setState(final int state) {
        this.state = state;
    }

    /**
     * Sets the animation index.
     *
     * @param aniIndex the new animation index.
     */
    @Override
    public void setAniIndex(final int aniIndex) {
        this.aniIndex = aniIndex;
    }

    /**
     * Sets the animation tick.
     *
     * @param aniTick the new animation tick.
     */
    @Override
    public void setAniTick(final int aniTick) {
        this.aniTick = aniTick;
    }

    /**
     * Increments the animation index.
     */
    @Override
    public void incrementAniIndex() {
        this.aniIndex++;
    }

    /**
     * Increments the animation tick.
     */
    @Override
    public void incrementAniTick() {
        this.aniTick++;
    }
}
