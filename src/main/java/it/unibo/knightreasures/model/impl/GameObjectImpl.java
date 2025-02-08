package it.unibo.knightreasures.model.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.api.GameObject;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;

/**
 * Implementation of a generic game object in the KnightTreasures game.
 */
public abstract class GameObjectImpl implements GameObject {

    private int x, y, xOffset, yOffset;
    private final int objType;
    private Rectangle2D.Float hitbox;
    private boolean doAnimation;
    private boolean active = true;
    private int aniTick;
    private int aniIndex;

    /**
     * Constructor for the GameObjectImpl class.
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param objType the type of the object
     */
    public GameObjectImpl(final int x, final int y, final int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    /**
     * Gets the number of sprites for the given object type.
     *
     * @param objectType the type of the object
     * @return the number of sprites
     */
    protected int getSpriteAmount(final int objectType) {
        switch (objectType) {
            case ObjectsValues.RING, ObjectsValues.CUP, ObjectsValues.CROWN -> {
                return ObjectsValues.OBJECTS_SPRITES;
            }
            case ObjectsValues.CHEST -> {
                return ObjectsValues.CHEST_SPRITES;
            }
            default -> {
                return 1;
            }
        }
    }

    /**
     * Updates the animation tick of the object.
     */
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(objType)) {
                aniIndex = 0;
            }
        }
    }

    /**
     * Initializes the hitbox of the object.
     *
     * @param width the width of the hitbox
     * @param height the height of the hitbox
     */
    protected void initHitbox(final int width, final int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    /**
     * Resets the game object to its initial state. This includes resetting
     * animation indices and marking it as inactive.
     */
    @Override
    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = false;
        doAnimation = false;
    }

    /**
     * Draws the hitbox of the object for debugging purposes.
     *
     * @param g the Graphics object used for drawing
     * @param xLvlOffset the level offset for rendering position correction
     */
    @Override
    public void drawHitbox(final Graphics g, final int xLvlOffset) {
        g.setColor(Color.BLUE);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    /**
     * Gets the object type identifier.
     *
     * @return an integer representing the type of the object
     */
    @Override
    public int getObjType() {
        return objType;
    }

    /**
     * Gets the hitbox of the game object.
     *
     * @return the hitbox as a Rectangle2D.Float
     */
    @Override
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    /**
     * Checks if the object is currently active.
     *
     * @return {@code true} if the object is active, {@code false} otherwise
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the object's active state.
     *
     * @param active {@code true} to activate the object, {@code false} to
     * deactivate it
     */
    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * Enables or disables animation for the object.
     *
     * @param doAnimation {@code true} to enable animation, {@code false} to
     * disable it
     */
    @Override
    public void setDoAnimation(final boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    /**
     * Gets the current animation index.
     *
     * @return the current frame index of the animation
     */
    @Override
    public int getAniIndex() {
        return aniIndex;
    }

    /**
     * Sets the animation index.
     *
     * @param aniIndex the new animation frame index
     */
    @Override
    public void setAniIndex(final int aniIndex) {
        this.aniIndex = aniIndex;
    }

    /**
     * Gets the x-axis offset of the object.
     *
     * @return the x-axis offset
     */
    @Override
    public int getXOffset() {
        return xOffset;
    }

    /**
     * Gets the y-axis offset of the object.
     *
     * @return the y-axis offset
     */
    @Override
    public int getYOffset() {
        return yOffset;
    }

    /**
     * Sets the y-axis offset of the object.
     *
     * @param yOffset the new y-axis offset
     */
    @Override
    public void setYOffset(final int yOffset) {
        this.yOffset = yOffset;
    }

    /**
     * Sets the x-axis offset of the object.
     *
     * @param xOffset the new x-axis offset
     */
    @Override
    public void setXOffset(final int xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * Checks if the object is currently animating.
     *
     * @return {@code true} if the object is animating, {@code false} otherwise
     */
    @Override
    public boolean getDoAnimation() {
        return doAnimation;
    }

    /**
     * Sets the animation tick counter.
     *
     * @param aniTick the new animation tick count
     */
    @Override
    public void setAniTick(final int aniTick) {
        this.aniTick = aniTick;
    }

    /**
     * Gets the x-coordinate of the object.
     *
     * @return the x-coordinate
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the object.
     *
     * @param x the new x-coordinate
     */
    @Override
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the object.
     *
     * @return the y-coordinate
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the object.
     *
     * @param y the new y-coordinate
     */
    @Override
    public void setY(final int y) {
        this.y = y;
    }
}
