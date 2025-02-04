package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Physics;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

/**
 * Represents an enemy entity in the game.
 */
public class EnemyEntity extends EntityManager {

    private int aniIndex, enemyState;
    private int aniTick;
    private boolean firstUpdate = true, inAir;
    private float fallSpeed;
    private int walkDir = Directions.LEFT;

    /**
     * Constructs an enemy entity.
     *
     * @param x         the x-coordinate of the enemy.
     * @param y         the y-coordinate of the enemy.
     * @param width     the width of the enemy.
     * @param height    the height of the enemy.
     */
    public EnemyEntity(final float x, final float y, final int width, final int height) {
        super(x, y, width, height);
        initHitBox(x, y, width, height);
    }

    /**
     * Gets the number of sprite frames for a given enemy state.
     *
     * @param enemyState the current state of the enemy.
     * @return the number of sprite frames for the enemy's animation.
     */
    private int getSpriteAmount(final int enemyState) {
        switch (enemyState) {
            case SkeletonsValues.IDLE:
                return SkeletonsValues.IDLE_SPRITES;
            case SkeletonsValues.RUN:
                return SkeletonsValues.RUN_SPRITES;
            case SkeletonsValues.HURT:
                return SkeletonsValues.HURT_SPRITES;
            case SkeletonsValues.DIE:
                return SkeletonsValues.DIE_SPRITES;
            default:
                return SkeletonsValues.IDLE_SPRITES; // Default per evitare errori
        }
    }

    /**
     * Updates the enemy's animation state.
     */
    private void updateAnimation() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(enemyState)) {
                aniIndex = 0;
            }
        }
    }

    /**
     * Updates the enemy's movement and animation.
     *
     * @param lvlData the level data containing collision information.
     */
    public void update(final int[][] lvlData) {
        updateMove(lvlData);
        updateAnimation();
    }

    /**
     * Updates the enemy's movement logic.
     *
     * @param lvlData the level data for collision detection.
     */
    private void updateMove(final int[][] lvlData) {
        if (firstUpdate) {
            if (!HelpMethods.isEntityOnFloor(getHitbox(), lvlData)) {
                inAir = true;
            }
            firstUpdate = false;
        }
        if (inAir) {
            if (HelpMethods.canMoveHere(
                    getHitbox().x,
                    getHitbox().y + fallSpeed,
                    getHitbox().width,
                    getHitbox().height,
                    lvlData)) {
                getHitbox().y += fallSpeed;
                fallSpeed += Physics.GRAVITY;
            } else {
                inAir = false;
                getHitbox().y = HelpMethods.getEntityYPosNextToWall(getHitbox(), fallSpeed);
            }
        } else {
            switch (enemyState) {
                case SkeletonsValues.IDLE:
                    enemyState = SkeletonsValues.RUN;
                    break;
                case SkeletonsValues.RUN:
                    final float xSpeed = (walkDir == Directions.LEFT) ? -Skeletons.SPEED : Skeletons.SPEED;
                    if (HelpMethods.canMoveHere(
                            getHitbox().x + xSpeed,
                            getHitbox().y,
                            getHitbox().width,
                            getHitbox().height,
                            lvlData)) {
                        if (HelpMethods.isFloor(getHitbox(), xSpeed, lvlData)) {
                            getHitbox().x += xSpeed;
                            return;
                        }
                    }
                    changeWalkDir();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Changes the walking direction of the enemy.
     */
    private void changeWalkDir() {
        if (walkDir == Directions.LEFT) {
            walkDir = Directions.RIGHT;
        } else {
            walkDir = Directions.LEFT;
        }
    }

    /**
     * Gets the current animation index.
     *
     * @return the current animation index.
     */
    @Override
    public int getIndex() {
        return aniIndex;
    }

    /**
     * Gets the current enemy state.
     *
     * @return the enemy's state.
     */
    public int getEnemyState() {
        return enemyState;
    }

    /**
     * Updates the entity's state (not used in this class).
     */
    @Override
    public void update() {
    }
}
