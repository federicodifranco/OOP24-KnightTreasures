package it.unibo.knightreasures.heart.core.api;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.model.impl.ObjectManagerImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelManagerImpl;

/**
 * Represents the core gameplay functionality of the game.
 * Manages the game state, player interactions, enemy behavior, 
 * and object interactions within the game world.
 */
public interface Gameplay {

    /**
     * Verifica se il gioco è terminato.
     *
     * @return true se il gioco è finito (game over), false altrimenti.
     */
    boolean isGameOver();

    /**
     * Verifica se il gioco è attualmente in pausa.
     *
     * @return true se il gioco è in pausa, false altrimenti.
     */
    boolean isPaused();

    /**
     * Verifica se il livello è stato completato con successo.
     *
     * @return true se il livello è stato completato, false altrimenti.
     */
    boolean isLevelCompleted();

    /** 
     * Unpauses the game and resumes gameplay. 
     */
    void unpauseGame();

    /**
     * Handles the event when the game window loses focus.
     * It ensures that the player's movement is stopped.
     */
    void windowLostFocus();

    /**
     * Resets the game state, including player, enemies, and objects.
     */
    void resetAll();

    /**
     * Sets the game over state.
     *
     * @param gameOver True if the game is over, false otherwise.
     */
    void setGameOver(boolean gameOver);

    /**
     * Marks the level as completed.
     *
     * @param levelCompleted True if the level is completed, false otherwise.
     */
    void setLevelCompleted(boolean levelCompleted);

    /**
     * Loads the next level in the game.
     */
    void loadNextLvl();

    /**
     * Checks if an enemy was hit by the player's attack.
     *
     * @param attackBox The hitbox of the player's attack.
     */
    void checkEnemyHit(Rectangle2D.Float attackBox);

    /**
     * Checks if the player has opened a chest.
     *
     * @param hitbox The hitbox of the player.
     */
    void checkChestOpened(Rectangle2D.Float hitbox);

    /**
     * Checks if the player has collected a treasure.
     *
     * @param hitbox The hitbox of the player.
     */
    void checkCollectTreasure(Rectangle2D.Float hitbox);

    /**
     * Checks if the player has touched a spike and handles damage.
     *
     * @param player The player entity.
     * @param hearts The hearts manager that tracks player health.
     */
    void checkSpikeTouched(PlayerEntityImpl player, HeartsImpl hearts);

    /**
     * Sets the maximum level offset for scrolling.
     *
     * @param lvlOffset The maximum level offset.
     */
    void setMaxLvlOffset(int lvlOffset);

    /**
     * Retrieves the player entity instance.
     *
     * @return The player entity.
     */
    PlayerEntityImpl getPlayer();

    /**
     * Retrieves the enemy manager instance.
     *
     * @return The enemy manager.
     */
    EnemyManagerImpl getEnemyManager();

    /**
     * Retrieves the object manager instance.
     *
     * @return The object manager.
     */
    ObjectManagerImpl getObjectManager();

    /**
     * Retrieves the level manager instance.
     *
     * @return The level manager.
     */
    LevelManagerImpl getLevel();
}
