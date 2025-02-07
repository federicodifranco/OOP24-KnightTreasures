package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.Point;

interface PlayerEntity {

    void render(Graphics g, int lvlOffset);

    void setSpawn(Point spawn);

    void update();

    void loseHeart();

    void setAnimation();

    void loadLvlData(int[][] lvlData);

    int getLives();

    void updateLives();

    void resetDirBooleans();

    void setAttacking(boolean attacking);

    void setLeft(boolean left);

    void setRight(boolean right);

    void setJump(boolean jump);

    void resetAll();
}
