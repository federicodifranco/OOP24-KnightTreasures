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

public class GameplayTest {

    private GameplayImpl gameplay;
    private LevelImpl level;
    private EnemyManagerImpl enemyManager;
    private ObjectManagerImpl objectManager;
    private PlayerEntityImpl player;
    private HeartsImpl hearts;
    private SkeletonImpl skeleton;
    private List<TreasureImpl> treasures;

    @BeforeEach
    void setUp() {
        this.gameplay = new GameplayImpl(null);
        this.level = gameplay.getLevel().getCurrentLevel();
        this.enemyManager = gameplay.getEnemyManager();
        this.objectManager = gameplay.getObjectManager();
        this.player = gameplay.getPlayer();
        this.skeleton = level.getSkeletons().get(0);
        this.hearts = new HeartsImpl(0, 0);
        this.treasures = level.getTreasures();
    }

    /**
     * Verifica che il livello attuale contenga tutti i nemici.
     */
    @Test
    void testLevelHasEnemies() {
        assertNotNull(enemyManager);
        assertFalse(level.getSkeletons().isEmpty());
    }

    /**
     * Verifica che il livello attuale contenga tutti gli oggetti collezionabili e le spine.
     */
    @Test
    void testLevelHasObjects() {
        assertNotNull(objectManager);
        assertFalse(level.getTreasures().isEmpty());
        assertFalse(level.getChests().isEmpty());
        assertFalse(level.getSpikes().isEmpty());
    }

    /**
     * Verifica che il game over si attivi quando il player viene colpito 3 volte da un nemico.
     */
    @Test
    void testPlayerHitByEnemy() {
        int initialLives = player.getLives();
        Rectangle2D.Float enemyAttackBox = skeleton.getAttackbox();
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
     * Test per verificare che il player raccolga un tesoro quando lo tocca.
     */
    @Test
    void testPlayerCollectsTreasure() {
        int initialCollected = objectManager.isAllCollectedTreasures();
        TreasureImpl treasure = treasures.get(0);
        Rectangle2D.Float playerHitbox = player.getHitbox();
        playerHitbox.x = treasure.getHitbox().x;
        playerHitbox.y = treasure.getHitbox().y;
        objectManager.checkObjectTouched(playerHitbox);
        assertFalse(treasure.isActive());
        assertEquals(initialCollected + 1, objectManager.isAllCollectedTreasures());
    }

    /**
     * Test per verificare che quando tutti i tesori sono raccolti.
     */
    @Test
    void testAllTreasuresCollected() {
        for (TreasureImpl treasure : treasures) {
            Rectangle2D.Float playerHitbox = player.getHitbox();
            playerHitbox.x = treasure.getHitbox().x;
            playerHitbox.y = treasure.getHitbox().y;
            objectManager.checkObjectTouched(playerHitbox);
        }
        assertEquals(3, objectManager.isAllCollectedTreasures());
    }
}
