package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

import it.unibo.knightreasures.view.impl.LevelImpl;

public interface LevelManager {

    void loadNextLvl();

    void draw(Graphics g, int lvlOffset);

    LevelImpl getCurrentLevel();

    int getAmountOfLvls();

    int getLevelIndex();
}
