package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.model.api.Chest;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

/**
 * Represents a chest object in the game that can be opened to reveal treasures.
 * The chest has an animation state and can be interacted with by the player.
 */
public final class ChestImpl extends GameObjectImpl implements Chest {

    private boolean opened = false;

    /**
     * Constructs a new chest at the specified position with the given type.
     *
     * @param x       the x-coordinate of the chest.
     * @param y       the y-coordinate of the chest.
     * @param objType the type of the chest.
     */
    public ChestImpl(final int x, final int y, final int objType) {
        super(x, y, objType);
        createHitbox();
    }

    /**
     * Initializes the hitbox for the chest and applies the necessary offsets.
     */
    private void createHitbox() {
        initHitbox(ObjectConstants.CHEST_HITBOX_WIDTH, ObjectConstants.CHEST_HITBOX_HEIGHT);
        setYOffset(getYOffset() + ObjectConstants.CHEST_Y_OFFSET);
        setXOffset(getXOffset() + ObjectConstants.CHEST_X_OFFSET);
        getHitbox().y += getYOffset();
        getHitbox().x += getXOffset();
    }

    /**
     * Updates the animation of the chest when it is opened.
     * The animation stops when it reaches the last frame.
     */
    @Override
    public void update() {
        if (getDoAnimation()) {
            updateAnimationTick();
            if (getAniIndex() >= getSpriteAmount(ObjectsValues.CHEST) - ObjectsValues.CHEST_SPRITE_ANI 
                    && isOpened()) {
                setAniIndex(getSpriteAmount(ObjectsValues.CHEST) - ObjectsValues.CHEST_SPRITE_ANI);
                setDoAnimation(false);
            }
        }
    }

    /**
     * Checks if the chest is opened.
     *
     * @return {@code true} if the chest is opened, {@code false} otherwise.
     */
    @Override
    public boolean isOpened() {
        return opened;
    }

    /**
     * Sets the opened state of the chest.
     *
     * @param opened {@code true} to mark the chest as opened, {@code false} otherwise.
     */
    @Override
    public void setOpened(final boolean opened) {
        this.opened = opened;
    }

    /**
     * Enables or disables the chest opening animation.
     * The animation starts only if the chest is not already opened.
     *
     * @param animation {@code true} to start the animation, {@code false} otherwise.
     */
    public void setAnimation(final boolean animation) {
        if (!opened) {
            setDoAnimation(animation);
        }
    }

    /**
     * Resets the chest to its initial state.
     * This includes resetting animation, setting it to inactive, and closing it.
     */
    @Override
    public void reset() {
        setAniIndex(0);
        setAniTick(0);
        setAnimation(false);
        setOpened(false);
        setActive(true);
    }
}
