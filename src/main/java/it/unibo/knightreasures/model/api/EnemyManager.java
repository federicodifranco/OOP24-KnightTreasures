package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public interface EnemyManager {

    void addEnemies(LevelImpl level);

    void update(int[][] lvlData, PlayerEntityImpl player);

    void draw(Graphics g, int xLvlOffset);

    void drawSkeletons(Graphics g, int xLvlOffset);

    boolean hasActiveEnemies();

    void checkEnemyHit(Rectangle2D.Float attackBox);

    void resetAllEnemies();
}
