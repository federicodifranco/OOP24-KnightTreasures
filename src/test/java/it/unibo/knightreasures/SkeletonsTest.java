package it.unibo.knightreasures;

import java.awt.geom.Rectangle2D;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.model.impl.SkeletonImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

/** 
 * Test per verificare il comportamento degli scheletri nel gioco.
 */
class SkeletonsTest {

    private static final int DAMAGE = 1;
    private static final int MAX_LIVES = 3;

    private GameplayImpl gameplay;
    private PlayerEntityImpl player;
    private List<SkeletonImpl> skeletons;

    /**
     * Inizializza il gameplay e i nemici prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        gameplay = new GameplayImpl(null);
        final LevelImpl level = gameplay.getLevel().getCurrentLevel();
        final EnemyManagerImpl enemyManager = gameplay.getEnemyManager();
        enemyManager.addEnemies(level);
        player = gameplay.getPlayer();
        skeletons = level.getSkeletons();
    }

    /**
     * Verifica che gli scheletri siano presenti nel livello.
     */
    @Test
    void testSkeletonsSpawn() {
        assertFalse(skeletons.isEmpty());
    }

    /**
     * Verifica che il player venga colpito da uno scheletro.
     */
    @Test
    void testSkeletonAttacksPlayer() {
        final SkeletonImpl skeleton = skeletons.get(0);
        final Rectangle2D.Float attackBox = skeleton.getAttackBox();
        assertEquals(MAX_LIVES, player.getLives());
        final boolean hit = attackBox.intersects(player.getHitbox());
        assertTrue(hit);
        final int livesBefore = player.getLives();
        gameplay.checkEnemyHit(attackBox);
        assertTrue(player.getLives() < livesBefore);
        assertEquals(MAX_LIVES - DAMAGE, player.getLives());
    }

    /**
     * Verifica che gli scheletri muoiano dopo aver subito danni sufficienti.
     */
    @Test
    void testSkeletonsDieAfterDamage() {
        final SkeletonImpl skeleton = skeletons.get(0);
        for (int i = 0; i < MAX_LIVES; i++) {
            skeleton.hurt(DAMAGE);
        }
        assertFalse(skeleton.isActive());
    }

    /**
     * Verifica che il livello sia completato quando tutti gli scheletri sono sconfitti.
     */
    @Test
    void testLevelCompletesWhenAllSkeletonsAreDefeated() {
        for (final SkeletonImpl skeleton : skeletons) {
            while (skeleton.isActive()) {
                skeleton.hurt(DAMAGE);
            }
        }
        gameplay.update();
        assertTrue(gameplay.isLevelCompleted());
    }
}
