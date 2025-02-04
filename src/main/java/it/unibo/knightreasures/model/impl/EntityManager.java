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
     * The X and the Y coordinate of the entity.
     */
    private float x, y;

    /**
     * The width and the height of the entity.
     */
    private int width, height;

    /**
     * The hitbox used for collision detection.
     */
    private Rectangle2D.Float hitBox;

    private boolean inAir;
    private float airSpeed;
    private int aniIndex, aniTick, state;

    /**
     * Constructs an EntityManager instance.
     *
     * @param x the X coordinate of the entity.
     * @param y the Y coordinate of the entity.
     * @param width the width of the entity.
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
     * @param x the X coordinate of the hitbox.
     * @param y the Y coordinate of the hitbox.
     * @param width the width of the hitbox.
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

    public int getState() {
        return this.state;
    }

    public int getIndex() {
        return this.aniIndex;
    }

    public int getTick() {
        return this.aniTick;
    }

    public boolean getInAir() {
        return this.inAir;
    }

    public float getAirSpeed() {
        return this.airSpeed;
    }

    public void setInAir(final boolean inAir) {
        this.inAir = inAir;
    }

    public void setAirSpeed(final float airSpeed) {
        this.airSpeed = airSpeed;
    }

    public void setState(final int state) {
        this.state = state;
    }

    public void setAniIndex(final int aniIndex) {
        this.aniIndex = aniIndex;
    }

    public void setAniTick(final int aniTick) {
        this.aniTick = aniTick;
    }

    public void incrementAniIndex() {
        this.aniIndex++;
    }

    public void incrementAniTick() {
        this.aniTick++;
    }
}