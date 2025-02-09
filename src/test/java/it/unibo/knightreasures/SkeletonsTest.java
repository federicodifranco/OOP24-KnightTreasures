package it.unibo.knightreasures;

import java.awt.geom.Rectangle2D;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.model.impl.SkeletonImpl;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.view.impl.LevelImpl;

public class SkeletonsTest {

    private GameplayImpl gameplay;
    private EnemyManagerImpl enemyManager;
    private PlayerEntityImpl player;
    private LevelImpl level;
    private List<SkeletonImpl> skeletons;

    @BeforeEach
    void setUp() {
        this.gameplay = new GameplayImpl(null);
        this.enemyManager = gameplay.getEnemyManager();
        this.player = gameplay.getPlayer();
        this.level = gameplay.getLevel().getCurrentLevel();
        this.enemyManager.addEnemies(gameplay.getLevel().getCurrentLevel());
        this.skeletons = level.getSkeletons();
    }

    @Test
    void testSkeletonsArePresent() {
        assertFalse(skeletons.isEmpty());
    }

    @Test
    void testSkeletonsMove() {
        for (SkeletonImpl skeleton : skeletons) {
            float initialX = skeleton.getHitbox().x;
            skeleton.update(gameplay.getLevel().getCurrentLevel().getLevelData(), player);
            assertNotEquals(initialX, skeleton.getHitbox().x);
        }
    }

    @Test
    void testSkeletonAttacksPlayer() {
        SkeletonImpl skeleton = skeletons.get(0);
        skeleton.getHitbox().x = player.getHitbox().x - SkeletonsValues.RANGE_TO_SEE_PLAYER;
        skeleton.getHitbox().y = player.getHitbox().y;
        skeleton.update(gameplay.getLevel().getCurrentLevel().getLevelData(), player);
        Rectangle2D.Float attackBox = skeleton.getAttackBox();
        boolean hit = attackBox.intersects(player.getHitbox());
        assertTrue(hit);
        int livesBefore = player.getLives();
        gameplay.checkEnemyHit(attackBox);
        assertTrue(player.getLives() < livesBefore);
    }

    @Test
    void testSkeletonDiesWhenHealthIsZero() {
        SkeletonImpl skeleton = skeletons.get(0);
        skeleton.hurt(SkeletonsValues.NUM_LIVES);
        assertFalse(skeleton.isActive());
    }

    @Test
    void testAllSkeletonsDeadCompletesLevel() {
        for (SkeletonImpl skeleton : skeletons) {
            skeleton.hurt(SkeletonsValues.NUM_LIVES);
        }
        enemyManager.update(gameplay.getLevel().getCurrentLevel().getLevelData(), player);
        assertTrue(gameplay.getEnemyManager().hasActiveEnemies() == false);
        assertTrue(gameplay.isLevelCompleted());
    }
}
