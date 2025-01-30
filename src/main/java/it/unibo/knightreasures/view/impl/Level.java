package it.unibo.knightreasures.view.impl;

public class Level {

    private int [][] lvlData;

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

}
