package it.unibo.knightreasures.model.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.api.EnemyEntity;
import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Physics;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Represents an enemy entity in the game. This class provides basic behavior
 * for all enemy entities, including movement, health, and state management.
 */
public abstract class EnemyEntityImpl extends EntityManagerImpl implements EnemyEntity {

    private int aniIndex, aniTick, enemyState, tileY, walkDir = Directions.LEFT;
    private boolean firstUpdate = true, attackChecked, active = true;
    private final float attackDistance;
    private float fallSpeed;

    /**
     * Constructs an enemy entity.
     *
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @param width The width of the enemy.
     * @param height The height of the enemy.
     */
    public EnemyEntityImpl(final float x, final float y, final int width, final int height) {
        super(x, y, width, height);
        this.attackDistance = Window.TILES_SIZE;
    }

    /**
     * Initializes the enemy's hitbox, health, and other necessary attributes.
     */
    public void initialize() {
        initHitBox(getWidth(), getHeight());
        setMaxHealth(SkeletonsValues.NUM_LIVES);
        setCurrentHealth(getMaxHealth());
    }

    private int getSpriteAmount(final int enemyState) {
        return switch (enemyState) {
            case SkeletonsValues.IDLE ->
                SkeletonsValues.IDLE_SPRITES;
            case SkeletonsValues.RUN ->
                SkeletonsValues.RUN_SPRITES;
            case SkeletonsValues.ATTACK ->
                SkeletonsValues.ATTACK_SPRITES;
            case SkeletonsValues.HURT ->
                SkeletonsValues.HURT_SPRITES;
            case SkeletonsValues.DIE ->
                SkeletonsValues.DIE_SPRITES;
            default ->
                SkeletonsValues.IDLE_SPRITES;
        };
    }

    /**
     * Checks if this is the first update and adjusts enemy state accordingly.
     *
     * @param lvlData The level data for collision checks.
     */
    protected void firstUpdateCheck(final int[][] lvlData) {
        if (firstUpdate) {
            if (!HelpMethods.isEntityOnFloor(getHitbox(), lvlData)) {
                setInAir(true);
            }
            firstUpdate = false;
        }
    }

    /**
     * Checks if the enemy's attack hit the player.
     *
     * @param attackBox The attack hitbox.
     * @param player The player entity.
     */
    protected void checkPlayerHit(final Rectangle2D.Float attackBox, final PlayerEntityImpl player) {
        if (attackBox.intersects(player.getHitbox())) {
            player.loseHeart();
        }
        attackChecked = true;
    }

    /**
     * Handles enemy falling and gravity.
     *
     * @param lvlData The level data for collision checks.
     */
    protected void updateInAir(final int[][] lvlData) {
        if (HelpMethods.canMoveHere(getHitbox().x, getHitbox().y + fallSpeed, getHitbox().width, getHitbox().height, lvlData)) {
            getHitbox().y += fallSpeed;
            fallSpeed += Physics.GRAVITY;
        } else {
            setInAir(false);
            getHitbox().y = HelpMethods.getEntityYPosNextToWall(getHitbox(), fallSpeed);
            tileY = (int) (getHitbox().y / Window.TILES_SIZE);
        }
    }

    /**
     * Handles enemy movement and walking direction.
     *
     * @param lvlData The level data for collision checks.
     */
    protected void move(final int[][] lvlData) {
        final float xSpeed;
        if (getWalkDir() == Directions.LEFT) {
            xSpeed = -Skeletons.SPEED;
            if (HelpMethods.canMoveHere(getHitbox().x + xSpeed, getHitbox().y, getHitbox().width, getHitbox().height, lvlData)
                    && HelpMethods.isFloor(getHitbox(), xSpeed, lvlData)) {
                getHitbox().x += xSpeed;
                return;
            }
        } else {
            xSpeed = Skeletons.SPEED;
            if (HelpMethods.canMoveHere(getHitbox().x + xSpeed, getHitbox().y, getHitbox().width, getHitbox().height, lvlData)
                    && HelpMethods.isFloor(new Rectangle2D.Float(getHitbox().x + getHitbox().width, getHitbox().y,
                            LevelsValues.FLOOR_OFFSET, getHitbox().height), xSpeed, lvlData)) {
                getHitbox().x += xSpeed;
                return;
            }
        }
        changeWalkDir();
    }

    /**
     * Sets a new state for the enemy.
     *
     * @param enemyState The new state.
     */
    protected void newState(final int enemyState) {
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Turns the enemy towards the player.
     *
     * @param player The player entity.
     */
    protected void turnTowardsPlayer(final PlayerEntityImpl player) {
        walkDir = (player.getHitbox().x > getHitbox().x) ? Directions.RIGHT : Directions.LEFT;
    }

    /**
     * Checks if the enemy has line of sight to the player.
     *
     * @param lvlData The level data for collision checks.
     * @param player The player entity.
     * @return True if the enemy can see the player, false otherwise.
     */
    protected boolean canSeePlayer(final int[][] lvlData, final PlayerEntityImpl player) {
        final int playerTileY = (int) (player.getHitbox().y / Window.TILES_SIZE);
        return playerTileY == tileY && isPlayerInRange(player)
                && HelpMethods.isSightClear(lvlData, getHitbox(), player.getHitbox(), tileY);
    }

    /**
     * Checks if the player is within a certain range.
     *
     * @param player The player entity.
     * @return True if the player is within range, false otherwise.
     */
    protected boolean isPlayerInRange(final PlayerEntityImpl player) {
        return Math.abs(player.getHitbox().x - getHitbox().x) <= attackDistance * SkeletonsValues.RANGE_TO_SEE_PLAYER;
    }

    /**
     * Checks if the player is close enough for an attack.
     *
     * @param player The player entity.
     * @return True if the player is in attack range, false otherwise.
     */
    protected boolean isPlayerCloseForAttack(final PlayerEntityImpl player) {
        return Math.abs(player.getHitbox().x - getHitbox().x) <= attackDistance;
    }

    /**
     * Updates enemy animation based on state.
     */
    protected void updateAnimation() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(enemyState)) {
                aniIndex = 0;
                switch (enemyState) {
                    case SkeletonsValues.ATTACK, SkeletonsValues.HURT -> {
                        enemyState = SkeletonsValues.IDLE;
                    }
                    case SkeletonsValues.DIE -> {
                        active = false;
                    }
                    default -> {
                        // No action required for unhandled keys
                    }
                }
            }
        }
    }

    /**
     * Changes the enemy's walking direction.
     */
    protected void changeWalkDir() {
        walkDir = (walkDir == Directions.LEFT) ? Directions.RIGHT : Directions.LEFT;
    }

    /**
     * Reduces the enemy's health when attacked.
     *
     * @param amount The amount of damage taken.
     */
    @Override
    public void hurt(final int amount) {
        setCurrentHealth(getCurrentHealth() - amount);
        if (getCurrentHealth() <= 0) {
            newState(SkeletonsValues.DIE);
        } else {
            newState(SkeletonsValues.HURT);
        }
    }

    /**
     * Checks if the enemy is still active.
     *
     * @return {@code true} if the enemy is active, {@code false} otherwise.
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Resets the enemy to its initial state.
     */
    @Override
    public void resetEnemy() {
        getHitbox().x = getX();
        getHitbox().y = getY();
        firstUpdate = true;
        setCurrentHealth(getMaxHealth());
        newState(SkeletonsValues.IDLE);
        active = true;
        setAirSpeed(0);
    }

    /**
     * Gets the current enemy state.
     *
     * @return The enemy state.
     */
    @Override
    public int getEnemyState() {
        return enemyState;
    }

    /**
     * Checks if it's the first update.
     *
     * @return {@code true} if it's the first update, {@code false} otherwise.
     */
    @Override
    public boolean isFirstupdate() {
        return firstUpdate;
    }

    /**
     * Returns the current enemy animation index.
     *
     * @return The animation index.
     */
    @Override
    public int getAniIndex() {
        return aniIndex;
    }

    /**
     * Checks if the enemy has already checked for an attack.
     *
     * @return True if the attack has been checked, false otherwise.
     */
    @Override
    public boolean isAttackChecked() {
        return attackChecked;
    }

    /**
     * Sets whether the attack has been checked.
     *
     * @param attackChecked True if the attack has been checked, false
     * otherwise.
     */
    @Override
    public void setAttackChecked(final boolean attackChecked) {
        this.attackChecked = attackChecked;
    }

    /**
     * Gets the enemy's current walking direction.
     *
     * @return The walking direction.
     */
    @Override
    public int getWalkDir() {
        return walkDir;
    }
}
