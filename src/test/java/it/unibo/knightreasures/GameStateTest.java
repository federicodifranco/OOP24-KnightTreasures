package it.unibo.knightreasures;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.view.impl.GameOver;
import it.unibo.knightreasures.view.impl.LevelImpl;
import it.unibo.knightreasures.view.impl.LvlCompleted;
import it.unibo.knightreasures.view.impl.Pause;

public class GameStateTest {

    private GameplayImpl gameplay;
    private ApplicationImpl game;
    private Pause pauseOverlay;
    private GameOver gameOverOverlay;
    private LvlCompleted lvlCompletedOverlay;
    private EnemyManagerImpl enemyManager;
    private LevelImpl level;

    @BeforeEach
    void setUp() {
        game = new ApplicationImpl();
        gameplay = new GameplayImpl(game);
        pauseOverlay = new Pause(gameplay, gameplay.getLevel(), game);
        gameOverOverlay = new GameOver(gameplay, gameplay.getLevel(), game);
        lvlCompletedOverlay = new LvlCompleted(gameplay, gameplay.getLevel(), game);
        level = gameplay.getLevel().getCurrentLevel();
        enemyManager = gameplay.getEnemyManager();
    }

    @Test
    void testPauseScreenAppears() {
        gameplay.unpauseGame();
        assertFalse(gameplay.isPaused());
        gameplay.keyPressed(new java.awt.event.KeyEvent(new java.awt.Button(), 0, 0, 0, java.awt.event.KeyEvent.VK_P, 'P'));
        assertTrue(gameplay.isPaused());
    }

    @Test
    void testLevelCompletedScreenAppears() {
        assertTrue(enemyManager.hasActiveEnemies());
        level.getSkeletons().forEach(skeleton -> {
            while (skeleton.isActive()) {
                enemyManager.checkEnemyHit(skeleton.getAttackBox());
            }
        });
        gameplay.update();
        assertFalse(enemyManager.hasActiveEnemies());
        assertTrue(gameplay.isLevelCompleted());
    }

    @Test
    void testGameOverScreenAppears() {
        gameplay.getPlayer().loseHeart();
        gameplay.getPlayer().loseHeart();
        gameplay.getPlayer().loseHeart();
        gameplay.update();
        assertTrue(gameplay.isGameOver());
    }
}
