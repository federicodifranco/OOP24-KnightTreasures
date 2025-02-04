package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

/**
 * Represents a skeleton enemy in the game.
 */
public class Skeleton extends EnemyEntity {

    /**
     * Constructs a Skeleton enemy at the specified position.
     *
     * @param x the x-coordinate of the skeleton.
     * @param y the y-coordinate of the skeleton.
     */
    public Skeleton(final float x, final float y) {
        super(x, y, Skeletons.WIDTH, Skeletons.HEIGHT, SkeletonsValues.SKELETON);
        initHitBox(x, y, Skeletons.HITBOX_WIDTH, Skeletons.HITBOX_HEIGHT);
    }
}
