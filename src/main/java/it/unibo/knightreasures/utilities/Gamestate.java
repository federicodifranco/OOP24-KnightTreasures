package it.unibo.knightreasures.utilities;

/**
 * Represents the different possible game states.
 */
public enum Gamestate {

    /** The game is in progress. */
    PLAYING,

    /** The main menu is active. */
    MENU,

    /** The settings menu is open. */
    SETTINGS,

    /** The game is exiting. */
    EXIT;

    /** The current state of the game. */
    private static Gamestate state = MENU;

    /**
     * Gets the current game state.
     *
     * @return the current game state.
     */
    public static Gamestate getState() {
        return state;
    }

    /**
     * Sets the game state.
     *
     * @param newState the new game state to be set.
     */
    public static void setState(final Gamestate newState) {
        state = newState;
    }
}
