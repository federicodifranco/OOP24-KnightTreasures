package it.unibo.knightreasures.heart.core.api;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.model.impl.ObjectManagerImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.view.impl.HeartsImpl;

public interface Gameplay {

    void unpauseGame();

    PlayerEntityImpl getPlayer();

    void windowLostFocus();

    void resetAll();

    void setGameOver(boolean gameOver);

    void checkEnemyHit(Rectangle2D.Float attackBox);

    void checkChestOpened(Rectangle2D.Float hitbox);

    void checkCollectTreasure(Rectangle2D.Float hitbox);

    void checkSpikeTouched(PlayerEntityImpl player, HeartsImpl hearts);

    EnemyManagerImpl getEnemyManager();

    ObjectManagerImpl getObjectManager();

    void setMaxLvlOffset(int lvlOffset);

    void setLevelCompleted(boolean levelCompleted);

    void loadNextLvl();
}
