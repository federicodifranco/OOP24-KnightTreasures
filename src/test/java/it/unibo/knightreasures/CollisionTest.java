package it.unibo.knightreasures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Classe di test per verificare la gestione delle collisioni all'interno del gioco.
 */
class CollisionTest {

    private static final int MOVE_UP = 50;
    private static final int MOVE_RIGHT = 200;

    private PlayerEntityImpl player;
    private int[][] levelData;
    private LevelImpl level;

    /**
     * Inizializza il gameplay e i dati di livello prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        final GameplayImpl gameplay = new GameplayImpl(null);
        player = gameplay.getPlayer();
        level = gameplay.getLevel().getCurrentLevel();
        levelData = level.getLevelData();
    }

    /**
     * Verifica che il giocatore sia correttamente a contatto con il pavimento.
     */
    @Test
    void testPlayerOnFloor() {
        assertTrue(player.inAir());
        while (player.inAir()) {
            player.update();
        }
        assertFalse(player.inAir());
        assertTrue(HelpMethods.isEntityOnFloor(player.getHitbox(), levelData));
    }

    /**
     * Verifica che il giocatore cada correttamente quando non è sul pavimento.
     */
    @Test
    void testPlayerFallsWhenNotOnFloor() {
        player.getHitbox().y -= MOVE_UP;
        assertFalse(HelpMethods.isEntityOnFloor(player.getHitbox(), levelData));
    }

    /**
     * Verifica che il giocatore non possa attraversare un muro.
     */
    @Test
    void testPlayerHitsWall() {
        final float originalX = player.getHitbox().x;
        player.getHitbox().x += MOVE_RIGHT;

        if (!HelpMethods.canMoveHere(player.getHitbox().x, player.getHitbox().y,
                player.getHitbox().width, player.getHitbox().height, levelData)) {
            assertEquals(HelpMethods.getEntityXPosNextToWall(player.getHitbox(), 1), player.getHitbox().x);
        } else {
            assertNotEquals(originalX, player.getHitbox().x);
        }
    }

    /**
     * Verifica che il giocatore non possa attraversare il soffitto.
     */
    @Test
    void testPlayerHitsCeiling() {
        final float originalY = player.getHitbox().y;
        player.getHitbox().y -= MOVE_UP;

        if (!HelpMethods.canMoveHere(player.getHitbox().x, player.getHitbox().y,
                player.getHitbox().width, player.getHitbox().height, levelData)) {
            assertEquals(HelpMethods.getEntityYPosNextToWall(player.getHitbox(), -1), player.getHitbox().y);
        } else {
            assertNotEquals(originalY, player.getHitbox().y);
        }
    }

    /**
     * Verifica che i nemici possano camminare solo su superfici solide e cambino direzione ai bordi.
     */
    @Test
    void testEnemyWalkableTiles() {
        level.getSkeletons().forEach(enemy -> {
            while (enemy.isInAir()) {
                enemy.update(levelData, player);
            }
            final float initialX = enemy.getHitbox().x;
            for (int i = 0; i < MOVE_UP; i++) {
                enemy.update(levelData, player);
            }
            final float newX = enemy.getHitbox().x;
            assertNotEquals(initialX, newX);
        });
    }
}
