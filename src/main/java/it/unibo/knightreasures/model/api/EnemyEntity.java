package it.unibo.knightreasures.model.api;

/**
 * Represents an enemy entity in the game.
 * This interface provides methods to manage the enemy's state, animations, and interactions.
 */
public interface EnemyEntity {

    /**
     * Checks if the enemy is active.
     *
     * @return True if the enemy is active, false otherwise.
     */
    boolean isActive();

    /**
     * Checks if the enemy's attack has been checked.
     *
     * @return True if the attack has been checked, false otherwise.
     */
    boolean isAttackChecked();

    /**
     * Checks if this is the first update for the enemy.
     *
     * @return True if it's the first update, false otherwise.
     */
    boolean getFirstupdate();

    /**
     * Retrieves the current animation index of the enemy.
     *
     * @return The current animation index.
     */
    int getAniIndex();

    /**
     * Retrieves the current state of the enemy.
     *
     * @return The integer value representing the enemy's state.
     */
    int getEnemyState();

    /**
     * Retrieves the enemy's current walking direction.
     *
     * @return The integer representing the walking direction (e.g., left or right).
     */
    int getWalkDir();

    /**
     * Reduces the enemy's health when hit.
     *
     * @param amount The amount of damage inflicted on the enemy.
     */
    void hurt(int amount);

    /**
     * Resets the enemy to its initial state.
     */
    void resetEnemy();

    /**
     * Sets whether the enemy's attack has been checked.
     *
     * @param attackChecked True if the attack has been checked, false otherwise.
     */
    void setAttackChecked(boolean attackChecked);
}
