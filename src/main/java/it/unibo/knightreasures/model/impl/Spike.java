package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

public class Spike extends GameObject {

    public Spike(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    private void createHitbox() {
        initHitbox(ObjectConstants.SPIKE_HITBOX_WIDTH, ObjectConstants.SPIKE_HITBOX_HEIGHT);
        yOffset += ObjectConstants.SPIKE_OFFSET;
        hitbox.y += yOffset;
    }
}