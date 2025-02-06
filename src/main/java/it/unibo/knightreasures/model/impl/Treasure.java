package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;

public class Treasure extends GameObject {

    private float hoverOffset;
	private int hoverDir = ObjectsValues.HOVER_DIRECTION;

	public Treasure(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = true;
		initHitbox(ObjectConstants.TREASURE_HITBOX_WIDTH, ObjectConstants.TREASURE_HITBOX_HEIGHT);
	}

	private void updateHover() {
		hoverOffset += (ObjectConstants.HOVER_OFFSET * hoverDir);

		if (hoverOffset >= ObjectConstants.MAX_HOVER_OFFSET)
			hoverDir = -ObjectsValues.HOVER_DIRECTION;
		else if (hoverOffset < 0)
			hoverDir = ObjectsValues.HOVER_DIRECTION;

		hitbox.y = y + hoverOffset;
	}

	public void update() {
		updateAnimationTick();
		updateHover();
	}
}