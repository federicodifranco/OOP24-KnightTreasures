package it.unibo.knightreasures;

import java.awt.Button;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Test per verificare la corretta gestione degli stati di gioco.
 */
class GameStateTest {

    private static final int MAX_HEALTH_LOSSES = 3;
    private GameplayImpl gameplay;
    private EnemyManagerImpl enemyManager;
    private LevelImpl level;

    /**
     * Inizializza gli oggetti di gioco prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        gameplay = new GameplayImpl(null);
        level = gameplay.getLevel().getCurrentLevel();
        enemyManager = gameplay.getEnemyManager();
    }

    /**
     * Verifica che la schermata di pausa appaia quando il gioco viene messo in pausa.
     */
    @Test
    void testPauseScreenAppears() {
        gameplay.unpauseGame();
        assertFalse(gameplay.isPaused());
        gameplay.keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, 
                System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P'));
        assertTrue(gameplay.isPaused());
    }

    /**
     * Verifica che la schermata di completamento livello appaia quando tutti i nemici vengono sconfitti.
     */
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

    /**
     * Verifica che la schermata di game over appaia quando il player perde tutte le vite.
     */
    @Test
    void testGameOverScreenAppears() {
        for (int i = 0; i < MAX_HEALTH_LOSSES; i++) {
            gameplay.getPlayer().loseHeart();
        }
        gameplay.update();
        assertTrue(gameplay.isGameOver());
    }
}
