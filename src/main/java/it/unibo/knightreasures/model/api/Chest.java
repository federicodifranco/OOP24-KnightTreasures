package it.unibo.knightreasures.model.api;

/**
 * Represents a chest object in the game.
 * Chests can be opened and may contain treasures.
 */
public interface Chest {

    /**
     * Updates the chest state, handling animations if needed.
     */
    void update();

    /**
     * Sets whether the chest is opened.
     *
     * @param opened True if the chest is opened, false otherwise.
     */
    void setOpened(boolean opened);

    /**
     * Checks if the chest is opened.
     *
     * @return True if the chest is opened, false otherwise.
     */
    boolean isOpened();
}
