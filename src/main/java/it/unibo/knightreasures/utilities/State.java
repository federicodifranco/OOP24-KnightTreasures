package it.unibo.knightreasures.utilities;

import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.view.impl.MenuButton;

/**
 * Represents the state of the game, providing utility methods
 * for handling interactions and managing game state transitions.
 */
public class State {

    /** Reference to the main game application instance. */
    private final ApplicationImpl game;

    /**
     * Constructs a new State with the given game instance.
     *
     * @param game the game application instance.
     */
    public State(final ApplicationImpl game) {
        this.game = game;
    }

    /**
     * Checks if the mouse event occurred inside the bounds of a given menu button.
     *
     * @param e  the mouse event to check.
     * @param mb the menu button to verify against.
     * @return true if the mouse event is inside the button's bounds, false otherwise.
     */
    public boolean isIn(final MouseEvent e, final MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Gets the game instance associated with this state.
     *
     * @return the game application instance.
     */
    public ApplicationImpl getGame() {
        return game;
    }
}
