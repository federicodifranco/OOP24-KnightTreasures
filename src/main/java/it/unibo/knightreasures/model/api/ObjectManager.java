package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;

public interface ObjectManager {

    void update();

    void draw(Graphics g, int xLvlOffset);

    void checkSpikeTouched(PlayerEntityImpl player, HeartsImpl hearts);

    void checkObjectTouched(Rectangle2D.Float hitbox);

    void checkChestOpened(Rectangle2D.Float hitbox);

    void loadObjects(LevelImpl newLevel);

    void resetAllObjects();

    int isAllCollectedTreasures();
}
