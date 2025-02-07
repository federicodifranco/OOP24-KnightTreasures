package it.unibo.knightreasures.view.api;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface MenuButton {

    void draw(Graphics g);

    void update();

    boolean isMouseOver();

    void setMouseOver(boolean mouseOver);

    boolean isMousePressed();

    void setMousePressed(boolean mousePressed);

    Rectangle getBounds();

    void applyGameState();

    void resetBools();
}
