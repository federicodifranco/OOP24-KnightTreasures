package it.unibo.knightreasures.model.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.api.Skeleton;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

/**
 * Represents a skeleton enemy in the game. The skeleton can move, detect the
 * player, and attack when in range.
 */
public class SkeletonImpl extends EnemyEntityImpl implements Skeleton {

    private int attackxOffsetX;
    private Rectangle2D.Float attackbox;

    /**
     * Constructs a Skeleton enemy at the specified position.
     *
     * @param x the x-coordinate of the skeleton.
     * @param y the y-coordinate of the skeleton.      
     */
    public SkeletonImpl(final float x, final float y) {
        super(x, y, Skeletons.WIDTH, Skeletons.HEIGHT);
        this.attackbox = getAttacktbox();
        initHitBox(Skeletons.HITBOX_WIDTH, Skeletons.HITBOX_HEIGHT);
        initAttackBox();
    }

    /**
     * Initializes the attack box for the skeleton.
     */
    private void initAttackBox() {
        attackbox = new Rectangle2D.Float(getX(), getY(), Skeletons.ATTACKBOX_WIDTH, Skeletons.ATTACKBOX_HEIGHT);
        attackxOffsetX = Skeletons.ATTACKBOX_OFFSET_X;
    }

    /**
     * Updates the position of the attack box to match the skeleton's movement.
     */
    private void updateAttackBox() {
        attackbox.x = getHitbox().x - attackxOffsetX;
        attackbox.y = getHitbox().y;
    }

    /**
     * Defines the skeleton's behavior based on its current state. The skeleton
     * moves, detects the player, and attacks when close.
     *
     * @param lvlData the level data for collision and movement checks.
     * @param player the player entity to track and attack.
     */
    protected void updateBehavior(final int[][] lvlData, final PlayerEntityImpl player) {
        if (getFirstupdate()) {
            firstUpdateCheck(lvlData);
        }
        if (isInAir()) {
            updateInAir(lvlData);
        } else {
            switch (getEnemyState()) {
                case SkeletonsValues.IDLE ->
                    newState(SkeletonsValues.RUN);
                case SkeletonsValues.RUN -> {
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                    }
                    if (isPlayerCloseForAttack(player) && canSeePlayer(lvlData, player)) {
                        newState(SkeletonsValues.ATTACK);
                    }
                    move(lvlData);
                }
                case SkeletonsValues.ATTACK -> {
                    if (getAniIndex() == 0) {
                        setAttackChecked(false);
                    }
                    if (getAniIndex() == SkeletonsValues.ATTACK_INDEX && !isAttackChecked()) {
                        checkPlayerHit(attackbox, player);
                        setAttackChecked(true);
                    }
                }
                default -> {
                    // No action required for unhandled states
                }
            }
        }
    }

    /**
     * Updates the skeleton's behavior, animations, and attack box. This method
     * is called every frame to ensure that the skeleton reacts appropriately.
     *
     * @param lvlData the level data for collision and movement checks.
     * @param player the player entity to track and attack.
     */
    @Override
    public void update(final int[][] lvlData, final PlayerEntityImpl player) {
        updateBehavior(lvlData, player);
        updateAnimation();
        updateAttackBox();
    }

    /**
     * Determines the X-coordinate adjustment needed for flipping the sprite.
     *
     * @return the flipped X position based on the walking direction.
     */
    @Override
    public int flipX() {
        return (getWalkDir() == Directions.LEFT) ? Skeletons.WIDTH : 0;
    }

    /**
     * Determines the width adjustment needed for flipping the sprite.
     *
     * @return -1 if the skeleton is facing left, 1 otherwise.
     */
    @Override
    public int flipW() {
        return (getWalkDir() == Directions.LEFT) ? -1 : 1;
    }

    @Override
    public void update() {
        // Not implemented
    }
}
