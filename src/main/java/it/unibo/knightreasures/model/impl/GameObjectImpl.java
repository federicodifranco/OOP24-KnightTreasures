package it.unibo.knightreasures.model.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.api.GameObject;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;

public abstract class GameObjectImpl implements GameObject{

    protected int x, y, objType, xOffset, yOffset;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;

    public GameObjectImpl(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    protected int getSpriteAmount(final int objectType) {
        switch (objectType) {
            case ObjectsValues.RING, ObjectsValues.CUP, ObjectsValues.CROWN -> {
                return ObjectsValues.OBJECTS_SPRITES;
            }
            case ObjectsValues.CHEST -> {
                return ObjectsValues.CHEST_SPRITES;
            }
        }
        return 1;
    }

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

    protected void initHitbox(int width, int height) {
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
    public void drawHitbox(Graphics g, int xLvlOffset) {
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
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    @Override
    public int getAniIndex() {
        return aniIndex;
    }

    @Override
    public int getXOffset() {
        return xOffset;
    }

    @Override
    public int getYOffset() {
        return yOffset;
    }
}