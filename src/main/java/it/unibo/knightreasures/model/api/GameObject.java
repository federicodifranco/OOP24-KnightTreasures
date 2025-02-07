package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public interface GameObject {

    void reset();

    void drawHitbox(Graphics g, int xLvlOffset);

    int getObjType();

    Rectangle2D.Float getHitbox();

    boolean isActive();

    void setActive(boolean active);

    void setAnimation(boolean doAnimation);

    int getAniIndex();

    int getXOffset();

    int getYOffset();
}
