package it.unibo.knightreasures.model.api;

import java.awt.geom.Rectangle2D;

public interface EntityManager {

    Rectangle2D.Float getHitbox();

    int getState();

    int getIndex();

    int getCurrentHealth();

}
