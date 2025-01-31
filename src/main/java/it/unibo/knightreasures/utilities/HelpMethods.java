package it.unibo.knightreasures.utilities;

import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!isSolid(x, y, lvlData)) {
            if(!isSolid(x + width, y + height, lvlData)) {
                if(!isSolid(x + width, y, lvlData)) {
                    if(!isSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][]lvlData) {
        
        if (x < 0 || x >= Window.GAME_WIDTH) return true;
        if (y < 0 || y >= Window.GAME_HEIGHT) return true;

        float xIndex = x / Window.TILES_SIZE;
        float yIndex = y / Window.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= LevelsValues.TILE_1_NOT_SOLID || value < LevelsValues.TILE_2_NOT_SOLID || value != LevelsValues.TILE_3_NOT_SOLID) return true;

        return false;
    }
}