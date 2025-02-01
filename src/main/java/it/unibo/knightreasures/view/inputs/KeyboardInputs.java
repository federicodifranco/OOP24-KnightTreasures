package it.unibo.knightreasures.view.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.view.impl.ApplicationPanel;

/**
 * Handles keyboard inputs for the game, forwarding key events
 * to the appropriate game state.
 */
public final class KeyboardInputs implements KeyListener {

    /** Reference to the game panel. */
    private final ApplicationPanel gamePanel;

    /**
     * Constructs a new KeyboardInputs instance.
     *
     * @param gamePanel the game panel associated with the inputs.
     */
    public KeyboardInputs(final ApplicationPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Handles key typing events.
     *
     * @param e the key event.
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        // Not used, but required by KeyListener interface
    }

    /**
     * Handles key press events and processes them based on the current game state.
     *
     * @param e the key event.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                this.gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    /**
     * Handles key release events and processes them based on the current game
     * state.
     *
     * @param e the key event.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                this.gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
}
