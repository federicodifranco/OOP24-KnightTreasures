package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

public interface SoundButton {

    void draw(Graphics g);

    void update();

    boolean isMouseOver();

    void setMouseOver(boolean mouseOver);

    boolean isMousePressed();

    void setMousePressed(boolean mousePressed);

    void resetBools();

    boolean isMuted();

    void setMuted(boolean muted);
}
