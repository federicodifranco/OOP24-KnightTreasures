package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

public interface Stars {

    void updateStarStates(boolean enemiesInactive, int playerLives, int collectedTreasure);

    void draw(Graphics g);
}
