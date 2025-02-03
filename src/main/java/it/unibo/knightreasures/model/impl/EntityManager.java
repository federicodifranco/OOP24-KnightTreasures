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
        this.hitBox = new Rectangle2D.Float(x, y, (int) (width * Window.SCALE), (int) (height * Window.SCALE));
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
        hitBox = new Rectangle2D.Float(x, y, (int) (width * Window.SCALE), (int) (height * Window.SCALE));
    }

    /**
     * Gets the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    public Rectangle2D.Float getHitbox() {
        return new Rectangle2D.Float(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    /**
     * Gets the X coordinate of the entity.
     *
     * @return the X coordinate.
     */
    public float getX() {
        return x;
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
        return y;
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
        return width;
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
        return height;
    }

    /**
     * Sets the height of the entity.
     *
     * @param height the new height.
     */
    public void setHeight(final int height) {
        this.height = height;
    }
}
