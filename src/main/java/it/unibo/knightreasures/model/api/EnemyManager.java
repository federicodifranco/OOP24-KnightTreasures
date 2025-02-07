package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

public interface EnemyManager {

    void addEnemies(LevelImpl level);

    void update(int[][] lvlData, PlayerEntityImpl player);

    void draw(Graphics g, int xLvlOffset);

    void drawSkeletons(Graphics g, int xLvlOffset);

    boolean hasActiveEnemies();

    void checkEnemyHit(Rectangle2D.Float attackBox);

    void resetAllEnemies();
}
