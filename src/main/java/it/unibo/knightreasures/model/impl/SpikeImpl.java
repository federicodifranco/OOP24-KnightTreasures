package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

public class SpikeImpl extends GameObjectImpl {

    public SpikeImpl(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    private void createHitbox() {
        initHitbox(ObjectConstants.SPIKE_HITBOX_WIDTH, ObjectConstants.SPIKE_HITBOX_HEIGHT);
        setYOffset(getYOffset() + ObjectConstants.SPIKE_OFFSET);
        getHitbox().y += getYOffset();
    }
}