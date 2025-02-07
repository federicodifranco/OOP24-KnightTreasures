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
        yOffset += ObjectConstants.CHEST_Y_OFFSET;
        xOffset += ObjectConstants.CHEST_X_OFFSET;
        hitbox.y += yOffset;
        hitbox.x += yOffset;
    }

    @Override
    public void update() {
        if (doAnimation) {
            updateAnimationTick();
            if (aniIndex >= getSpriteAmount(ObjectsValues.CHEST) - ObjectsValues.CHEST_SPRITE_ANI && isOpened()) {
                aniIndex = getSpriteAmount(ObjectsValues.CHEST) - ObjectsValues.CHEST_SPRITE_ANI;
                doAnimation = false;
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
            doAnimation = animation;
        }
    }

    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        doAnimation = false;
        opened = false;
        active = true;
    }
}