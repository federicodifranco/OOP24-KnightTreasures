package it.unibo.knightreasures;

import java.awt.geom.Rectangle2D;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.model.impl.ObjectManagerImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.model.impl.SkeletonImpl;
import it.unibo.knightreasures.model.impl.TreasureImpl;
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Test per verificare il corretto funzionamento del gameplay.
 */
class GameplayTest {

    private static final int TOTAL_TREASURES = 3;
    private GameplayImpl gameplay;
    private LevelImpl level;
    private EnemyManagerImpl enemyManager;
    private ObjectManagerImpl objectManager;
    private PlayerEntityImpl player;
    private HeartsImpl hearts;
    private SkeletonImpl skeleton;
    private List<TreasureImpl> treasures;

    /**
     * Inizializza gli oggetti necessari prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        gameplay = new GameplayImpl(null);
        level = gameplay.getLevel().getCurrentLevel();
        enemyManager = gameplay.getEnemyManager();
        objectManager = gameplay.getObjectManager();
        player = gameplay.getPlayer();
        skeleton = level.getSkeletons().get(0);
        hearts = new HeartsImpl(0, 0);
        treasures = level.getTreasures();
    }

    /**
     * Verifica che il livello contenga almeno un nemico.
     */
    @Test
    void testLevelHasEnemies() {
        assertNotNull(enemyManager);
        assertFalse(level.getSkeletons().isEmpty());
    }

    /**
     * Verifica che il livello contenga oggetti collezionabili e ostacoli.
     */
    @Test
    void testLevelHasObjects() {
        assertNotNull(objectManager);
        assertFalse(level.getTreasures().isEmpty());
        assertFalse(level.getChests().isEmpty());
        assertFalse(level.getSpikes().isEmpty());
    }

    /**
     * Verifica che il giocatore perda una vita quando viene colpito da un nemico.
     */
    @Test
    void testPlayerHitByEnemy() {
        final int initialLives = player.getLives();
        final Rectangle2D.Float enemyAttackBox = skeleton.getAttackbox();
        enemyManager.checkEnemyHit(enemyAttackBox);
        assertEquals(initialLives - 1, player.getLives());
    }

    /**
     * Verifica che il game over si attivi quando il player tocca le spine.
     */
    @Test
    void testGameOverBySpikes() {
        gameplay.checkSpikeTouched(player, hearts);
        assertTrue(gameplay.isGameOver());
    }

    /**
     * Verifica che il player raccolga un tesoro quando lo tocca.
     */
    @Test
    void testPlayerCollectsTreasure() {
        final int initialCollected = objectManager.getAllCollectedTreasures();
        final TreasureImpl treasure = treasures.get(0);
        final Rectangle2D.Float playerHitbox = player.getHitbox();
        playerHitbox.setRect(treasure.getHitbox().getX(), treasure.getHitbox().getY(),
                playerHitbox.getWidth(), playerHitbox.getHeight());
        objectManager.checkObjectTouched(playerHitbox);
        assertFalse(treasure.isActive());
        assertEquals(initialCollected + 1, objectManager.getAllCollectedTreasures());
    }

    /**
     * Verifica che il livello sia considerato completato quando tutti i tesori sono raccolti.
     */
    @Test
    void testAllTreasuresCollected() {
        for (final TreasureImpl treasure : treasures) {
            final Rectangle2D.Float playerHitbox = player.getHitbox();
            playerHitbox.setRect(treasure.getHitbox().getX(), treasure.getHitbox().getY(),
                    playerHitbox.getWidth(), playerHitbox.getHeight());
            objectManager.checkObjectTouched(playerHitbox);
        }
        assertEquals(TOTAL_TREASURES, objectManager.getAllCollectedTreasures());
    }
}
