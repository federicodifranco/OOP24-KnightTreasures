package it.unibo.knightreasures;

import java.awt.Button;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;

/**
 * Classe di test per verificare il comportamento degli input del giocatore.
 */
class InputsTest {

    private GameplayImpl gameplay;
    private PlayerEntityImpl player;

    /**
     * Inizializza il gameplay e il player prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        gameplay = new GameplayImpl(null);
        player = gameplay.getPlayer();
    }

    /**
     * Verifica che la pressione di un tasto non associato al movimento (es. 'W')
     * non influenzi la posizione del player.
     */
    @Test
    void testInvalidKeyPress() {
        final float initialX = player.getHitbox().x;
        final float initialY = player.getHitbox().y;

        gameplay.keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
        KeyEvent.VK_W, 'W'));
        gameplay.update();

        assertEquals(initialX, player.getHitbox().x);
        assertEquals(initialY, player.getHitbox().y);
    }

    /**
     * Verifica che il player si muova a destra quando viene premuto il tasto 'D'.
     */
    @Test
    void testMoveRight() {
        final float initialX = player.getHitbox().x;

        gameplay.keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
        KeyEvent.VK_D, 'D'));
        gameplay.update();

        assertTrue(player.getHitbox().x > initialX);

        gameplay.keyReleased(new KeyEvent(new Button(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, 
        KeyEvent.VK_D, 'D'));
        gameplay.update();
        assertFalse(player.isRight());
    }

    /**
     * Verifica che il player possa saltare quando viene premuto 'SPACE'.
     */
    @Test
    void testJumpAction() {
        assertFalse(player.inAir());

        gameplay.keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
        KeyEvent.VK_SPACE, ' '));
        gameplay.update();

        assertTrue(player.inAir());

        gameplay.keyReleased(new KeyEvent(new Button(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, 
        KeyEvent.VK_SPACE, ' '));
        gameplay.update();
        assertFalse(player.isJumping());
    }
}
