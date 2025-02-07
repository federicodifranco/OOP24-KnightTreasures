package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

public interface Hearts {

    void setCurrentHearts(int currentHearts);

    int getCurrentHearts();

    void draw(Graphics g);
}

