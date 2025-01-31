package it.unibo.knightreasures.utilities;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

public class HelpMethods {

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Window.TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPos = currentTile * Window.TILES_SIZE;
            int xOffset = (int) (Window.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - LevelsValues.FLOOR_OFFSET;
        } else {
            return currentTile * Window.TILES_SIZE;
        }
    }

    public static float GetEntityYPosNextToWall(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Window.TILES_SIZE);
        if (airSpeed > 0) {
            int tileYPos = currentTile * Window.TILES_SIZE;
            int yOffset = (int) (Window.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - LevelsValues.FLOOR_OFFSET;
        } else {
            return currentTile * Window.TILES_SIZE;
        }
    }

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!isSolid(x, y, lvlData)) {
            if (!isSolid(x + width, y + height, lvlData)) {
                if (!isSolid(x + width, y, lvlData)) {
                    if (!isSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {

        if (x < 0 || x >= Window.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Window.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Window.TILES_SIZE;
        float yIndex = y / Window.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        return (value >= LevelsValues.TILE_1_NOT_SOLID || value < LevelsValues.TILE_2_NOT_SOLID || value != LevelsValues.TILE_3_NOT_SOLID);
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }
}
