package it.unibo.knightreasures.model.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.api.Skeleton;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

/**
 * Represents a skeleton enemy in the game.
 */
public class SkeletonImpl extends EnemyEntityImpl implements Skeleton {

    private int attackxOffsetX;

    /**
     * Constructs a Skeleton enemy at the specified position.
     *
     * @param x the x-coordinate of the skeleton.
     * @param y the y-coordinate of the skeleton.      
     */
    public SkeletonImpl(final float x, final float y) {
        super(x, y, Skeletons.WIDTH, Skeletons.HEIGHT);
        initHitBox(Skeletons.HITBOX_WIDTH, Skeletons.HITBOX_HEIGHT);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(getX(), getY(), Skeletons.ATTACKBOX_WIDTH, Skeletons.ATTACKBOX_HEIGHT);
        attackxOffsetX = Skeletons.ATTACKBOX_OFFSET_X;
    }

    private void updateAttackBox() {
        attackBox.x = getHitbox().x - attackxOffsetX;
        attackBox.y = getHitbox().y;
    }

    @Override
    public void update(int[][] lvlData, PlayerEntityImpl player) {
        updateBehavior(lvlData, player);
        updateAnimation();
        updateAttackBox();
    }

    protected void updateBehavior(int[][] lvlData, PlayerEntityImpl player) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (isInAir()) {
            updateInAir(lvlData);
        } else {
            switch (getEnemyState()) {
                case SkeletonsValues.IDLE:
                    newState(SkeletonsValues.RUN);
                    break;
                case SkeletonsValues.RUN:
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                    }
                    if (isPlayerCloseForAttack(player) && canSeePlayer(lvlData, player)) {
                        newState(SkeletonsValues.ATTACK);
                    }
                    move(lvlData);
                    break;
                case SkeletonsValues.ATTACK: {
                    if (aniIndex == 0) {
                        attackChecked = false;
                    }
                    if (aniIndex == SkeletonsValues.ATTACK_INDEX && !attackChecked) {
                        checkPlayerHit(attackBox, player);
                        attackChecked = true;
                    }
                }
            }
        }
    }

    @Override
    public int flipX() {
        return (walkDir == Directions.LEFT) ? Skeletons.WIDTH : 0;
    }

    @Override
    public int flipW() {
        return (walkDir == Directions.LEFT) ? -1 : 1;
    }

    @Override
    public void update() {

    }
}
