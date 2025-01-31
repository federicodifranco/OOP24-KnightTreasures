package it.unibo.knightreasures.model.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.utilities.ViewConstants.Player;

public abstract class EntityManager {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    public EntityManager(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width + Player.HITBOX_WIDTH, height + Player.HITBOX_HEIGHT);
    }

    // protected void updateHitbox() {
    //     hitBox.x = (int)x;
    //     hitBox.y = (int)y;
    // }

    public Rectangle2D.Float getHitbox() {
        return hitBox;
    }

}
