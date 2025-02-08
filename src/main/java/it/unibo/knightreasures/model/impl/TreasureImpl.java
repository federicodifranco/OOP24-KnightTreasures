package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.model.api.Treasure;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

/**
 * Represents a collectible treasure in the game. Treasures can hover up and
 * down, adding a visual effect to indicate interactivity.
 */
public class TreasureImpl extends GameObjectImpl implements Treasure {

    private float hoverOffset;
    private int hoverDir = ObjectsValues.HOVER_DIRECTION;

    /**
     * Constructs a new treasure object at the specified position.
     *
     * @param x the x-coordinate of the treasure.
     * @param y the y-coordinate of the treasure.
     * @param objType the type identifier of the treasure.
     */
    public TreasureImpl(final int x, final int y, final int objType) {
        super(x, y, objType);
        setDoAnimation(true);
        initHitbox(ObjectConstants.TREASURE_HITBOX_WIDTH, ObjectConstants.TREASURE_HITBOX_HEIGHT);
    }

    private void updateHover() {
        hoverOffset += (ObjectConstants.HOVER_OFFSET * hoverDir);

        if (hoverOffset >= ObjectConstants.MAX_HOVER_OFFSET) {
            hoverDir = -ObjectsValues.HOVER_DIRECTION;
        } else if (hoverOffset < 0) {
            hoverDir = ObjectsValues.HOVER_DIRECTION;
        }
        getHitbox().y = getY() + hoverOffset;
    }

    /**
     * Updates the treasure's animation and hover effect. The animation tick is
     * incremented to control sprite changes, while the hover effect makes the
     * treasure float up and down.
     */
    @Override
    public void update() {
        updateAnimationTick();
        updateHover();
    }
}
