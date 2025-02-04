package it.unibo.knightreasures.model.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Represents a generic entity in the game, providing common functionality for
 * position, dimensions, and hitbox management.
 */
public abstract class EntityManager {

    /**
     * The X coordinate of the entity.
     */
    private float x;

    /**
     * The Y coordinate of the entity.
     */
    private float y;

    /**
     * The width of the entity.
     */
    private int width;

    /**
     * The height of the entity.
     */
    private int height;

    /**
     * The hitbox used for collision detection.
     */
    private Rectangle2D.Float hitBox;

    /**
     * Indicates whether the entity is in the air.
     */
    private boolean inAir;

    /**
     * The vertical speed of the entity.
     */
    private float airSpeed;

    /**
     * The current animation index.
     */
    private int aniIndex;

    /**
     * The animation tick counter.
     */
    private int aniTick;

    /**
     * The state of the entity.
     */
    private int state;

    /**
     * Constructs an EntityManager instance.
     *
     * @param x      the X coordinate of the entity.
     * @param y      the Y coordinate of the entity.
     * @param width  the width of the entity.
     * @param height the height of the entity.
     */
    public EntityManager(final float x, final float y, final int width, final int height) {
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
     * Draws the hitbox of the entity for debugging purposes.
     *
     * @param g the graphics object used for rendering.
     */
    protected void drawHitbox(final Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    /**
     * Initializes the hitbox of the entity.
     *
     * @param width  the width of the hitbox.
     * @param height the height of the hitbox.
     */
    protected void initHitBox(final float width, final float height) {
        this.hitBox = new Rectangle2D.Float(x, y, (int) (width * Window.SCALE), (int) (height * Window.SCALE));
    }

    /**
     * Gets the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    public Rectangle2D.Float getHitbox() {
        return this.hitBox;
    }

    /**
     * Gets the X coordinate of the entity.
     *
     * @return the X coordinate.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Sets the X coordinate of the entity.
     *
     * @param x the new X coordinate.
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate of the entity.
     *
     * @return the Y coordinate.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Sets the Y coordinate of the entity.
     *
     * @param y the new Y coordinate.
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Gets the width of the entity.
     *
     * @return the width of the entity.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the width of the entity.
     *
     * @param width the new width.
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Gets the height of the entity.
     *
     * @return the height of the entity.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the height of the entity.
     *
     * @param height the new height.
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Gets the current state of the entity.
     *
     * @return the current state.
     */
    public int getState() {
        return this.state;
    }

    /**
     * Gets the current animation index.
     *
     * @return the animation index.
     */
    public int getIndex() {
        return this.aniIndex;
    }

    /**
     * Gets the current animation tick.
     *
     * @return the animation tick.
     */
    public int getTick() {
        return this.aniTick;
    }

    /**
     * Checks if the entity is in the air.
     *
     * @return true if the entity is in the air, false otherwise.
     */
    public boolean getInAir() {
        return this.inAir;
    }

    /**
     * Gets the air speed of the entity.
     *
     * @return the air speed.
     */
    public float getAirSpeed() {
        return this.airSpeed;
    }

    /**
     * Sets whether the entity is in the air.
     *
     * @param inAir true if the entity is in the air, false otherwise.
     */
    public void setInAir(final boolean inAir) {
        this.inAir = inAir;
    }

    /**
     * Sets the air speed of the entity.
     *
     * @param airSpeed the new air speed.
     */
    public void setAirSpeed(final float airSpeed) {
        this.airSpeed = airSpeed;
    }

    /**
     * Sets the state of the entity.
     *
     * @param state the new state.
     */
    public void setState(final int state) {
        this.state = state;
    }

    /**
     * Sets the animation index.
     *
     * @param aniIndex the new animation index.
     */
    public void setAniIndex(final int aniIndex) {
        this.aniIndex = aniIndex;
    }

    /**
     * Sets the animation tick.
     *
     * @param aniTick the new animation tick.
     */
    public void setAniTick(final int aniTick) {
        this.aniTick = aniTick;
    }

    /**
     * Increments the animation index.
     */
    public void incrementAniIndex() {
        this.aniIndex++;
    }

    /**
     * Increments the animation tick.
     */
    public void incrementAniTick() {
        this.aniTick++;
    }
}
