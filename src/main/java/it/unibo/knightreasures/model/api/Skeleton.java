package it.unibo.knightreasures.model.api;

import it.unibo.knightreasures.model.impl.PlayerEntityImpl;

public interface Skeleton {

    void update(int[][] lvlData, PlayerEntityImpl player);

    int flipX();

    int flipW();
}
