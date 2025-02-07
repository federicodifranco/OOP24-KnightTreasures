package it.unibo.knightreasures.view.api;

import java.awt.Point;
import java.util.List;

import it.unibo.knightreasures.model.impl.ChestImpl;
import it.unibo.knightreasures.model.impl.SkeletonImpl;
import it.unibo.knightreasures.model.impl.SpikeImpl;
import it.unibo.knightreasures.model.impl.TreasureImpl;

public interface Level {

    List<TreasureImpl> getTreasures();

    List<ChestImpl> getChests();

    List<SpikeImpl> getSpikes();

    int getSpriteIndex(int x, int y);

    int[][] getLevelData();

    int getLvlOffset();

    List<SkeletonImpl> getSkeletons();

    Point getPlayerSpawn();
}
