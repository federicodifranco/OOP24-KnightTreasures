package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

public class Skeleton extends EnemyEntity{

    public Skeleton(float x, float y) {
        super(x, y, Skeletons.WIDTH, Skeletons.HEIGHT, SkeletonsValues.SKELETON);
        initHitBox(x, y, Skeletons.HITBOX_WIDTH, Skeletons.HITBOX_HEIGHT);
    }
}
