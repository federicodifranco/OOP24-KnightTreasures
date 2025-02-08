package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.model.api.Chest;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

public class ChestImpl extends GameObjectImpl implements Chest{

    private boolean opened = false;

    public ChestImpl(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    private void createHitbox() {
        initHitbox(ObjectConstants.CHEST_HITBOX_WIDTH, ObjectConstants.CHEST_HITBOX_HEIGHT);
        setYOffset(getYOffset() + ObjectConstants.CHEST_Y_OFFSET);
        setXOffset(getXOffset() + ObjectConstants.CHEST_X_OFFSET);
        getHitbox().y += getYOffset();
        getHitbox().x += getYOffset();
    }

    @Override
    public void update() {
        if (getAnimation()) {
            updateAnimationTick();
            if (getAniIndex() >= getSpriteAmount(ObjectsValues.CHEST) - ObjectsValues.CHEST_SPRITE_ANI && isOpened()) {
                setAniIndex(getSpriteAmount(ObjectsValues.CHEST) - ObjectsValues.CHEST_SPRITE_ANI);
                setAnimation(false);
            }
        }
    }

    @Override
    public boolean isOpened() {
        return opened;
    }

    @Override
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setAnimation(boolean animation) {
        if (!opened) {
            setAnimation(animation);
        }
    }

    public void reset() {
        setAniIndex(0);
        setAniTick(0);
        setAnimation(false);
        setOpened(false);
        setActive(true);
    }
}