package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.model.api.Spike;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

/**
 * Represents a spike trap in the game.
 * Spikes are static obstacles that damage the player upon contact.
 */
public class SpikeImpl extends GameObjectImpl implements Spike {

    /**
     * Constructs a new spike object at the specified position.
     *
     * @param x       the x-coordinate of the spike.
     * @param y       the y-coordinate of the spike.
     * @param objType the type identifier of the spike.
     */
    public SpikeImpl(final int x, final int y, final int objType) {
        super(x, y, objType);
    }

    /**
     * Initializes the spike by setting up its hitbox.
     * This method should be called after the object is created
     * to ensure the hitbox is correctly positioned and adjusted.
     */
    @Override
    public void initialize() {
        createHitbox();
    }

    /**
     * Initializes the hitbox for the spike.
     * The hitbox is slightly adjusted to match the actual sprite dimensions.
     */
    private void createHitbox() {
        initHitbox(ObjectConstants.SPIKE_HITBOX_WIDTH, ObjectConstants.SPIKE_HITBOX_HEIGHT);
        setYOffset(getYOffset() + ObjectConstants.SPIKE_OFFSET);
        getHitbox().y += getYOffset();
    }
}
