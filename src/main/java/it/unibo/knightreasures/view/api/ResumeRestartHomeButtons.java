package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

public interface ResumeRestartHomeButtons {

    void update();

    void draw(Graphics g);

    void resetBools();

    boolean isMouseOver();

    void setMouseOver(boolean mouseOver);

    boolean isMousePressed();

    void setMousePressed(boolean mousePressed);

}
