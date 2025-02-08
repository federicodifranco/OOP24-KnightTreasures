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

    private int x, y, objType, xOffset, yOffset;
    private Rectangle2D.Float hitbox;
    private boolean doAnimation;
    private boolean active = true;
    private int aniTick;
    private int aniIndex;

    /**
     * Constructor for the GameObjectImpl class.
     * 
     * @param x       the x-coordinate of the object
     * @param y       the y-coordinate of the object
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
     * @param width  the width of the hitbox
     * @param height the height of the hitbox
     */
    protected void initHitbox(final int width, final int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    @Override
    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = false;
        doAnimation = false;
    }

    @Override
    public void drawHitbox(final Graphics g, final int xLvlOffset) {
        g.setColor(Color.BLUE);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    @Override
    public int getObjType() {
        return objType;
    }

    @Override
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public void setAnimation(final boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    @Override
    public int getAniIndex() {
        return aniIndex;
    }

    public void setAniIndex(final int aniIndex) {
        this.aniIndex = aniIndex;
    }

    @Override
    public int getXOffset() {
        return xOffset;
    }

    @Override
    public int getYOffset() {
        return yOffset;
    }

    public void setYOffset(final int yOffset) {
        this.yOffset = yOffset;
    }

    public void setXOffset(final int xOffset) {
        this.xOffset = xOffset;
    }

    public boolean getAnimation() {
        return doAnimation;
    }

    public void setAniTick(final int aniTick) {
        this.aniTick = aniTick;
    }

    /**
     * Gets the x-coordinate of the object.
     * 
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the object.
     * 
     * @param x the new x-coordinate
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the object.
     * 
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the object.
     * 
     * @param y the new y-coordinate
     */
    public void setY(final int y) {
        this.y = y;
    }
}
