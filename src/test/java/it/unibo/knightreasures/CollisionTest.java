package it.unibo.knightreasures;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.view.impl.LevelImpl;

public class CollisionTest {

    private GameplayImpl gameplay;
    private PlayerEntityImpl player;
    private int[][] levelData;
    private LevelImpl level;

    @BeforeEach
    void setUp() {
        gameplay = new GameplayImpl(null);
        player = gameplay.getPlayer();
        level = gameplay.getLevel().getCurrentLevel();
        levelData = level.getLevelData();
    }

    /**
     * Verifica che il giocatore sia correttamente a contatto con il pavimento.
     */
    @Test
    void testPlayerOnFloor() {
        assertTrue(HelpMethods.isEntityOnFloor(player.getHitbox(), levelData));
    }

    /**
     * Verifica che il giocatore cada correttamente quando non è sul pavimento.
     */
    @Test
    void testPlayerFallsWhenNotOnFloor() {
        player.getHitbox().y -= 50;
        assertFalse(HelpMethods.isEntityOnFloor(player.getHitbox(), levelData));
    }

    /**
     * Verifica che un nemico sia sul pavimento.
     */
    @Test
    void testEnemyOnFloor() {
        level.getSkeletons().forEach(enemy -> {
            assertTrue(HelpMethods.isEntityOnFloor(enemy.getHitbox(), levelData));
        });
    }

    /**
     * Verifica che il giocatore non possa attraversare un muro.
     */
    @Test
    void testPlayerHitsWall() {
        float originalX = player.getHitbox().x;
        player.getHitbox().x += 200;

        if (!HelpMethods.canMoveHere(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height, levelData)) {
            assertTrue(player.getHitbox().x == HelpMethods.getEntityXPosNextToWall(player.getHitbox(), 1));
        } else {
            assertFalse(player.getHitbox().x == originalX);
        }
    }

    /**
     * Verifica che il giocatore non possa attraversare il soffitto.
     */
    @Test
    void testPlayerHitsCeiling() {
        float originalY = player.getHitbox().y;
        player.getHitbox().y -= 50;

        if (!HelpMethods.canMoveHere(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height, levelData)) {
            assertTrue(player.getHitbox().y == HelpMethods.getEntityYPosNextToWall(player.getHitbox(), -1));
        } else {
            assertFalse(player.getHitbox().y == originalY);
        }
    }

    /**
     * Verifica che i nemici possano camminare solo su superfici solide e cambino direzione ai bordi.
     */
    @Test
    void testEnemyWalkableTiles() {
        level.getSkeletons().forEach(enemy -> {
            int xStart = (int) (enemy.getHitbox().x / 32);
            int xEnd = xStart + 1;
            int yTile = (int) (enemy.getHitbox().y / 32);

            assertTrue(HelpMethods.isAllTileWalkable(xStart, xEnd, yTile, levelData));
        });
    }
}
