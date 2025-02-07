package it.unibo.knightreasures.view.api;

import java.awt.Rectangle;

public interface PauseButton {

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    Rectangle getBounds();

    void setBounds(Rectangle bounds);
}
