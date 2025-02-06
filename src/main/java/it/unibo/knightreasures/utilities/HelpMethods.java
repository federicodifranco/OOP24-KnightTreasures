package it.unibo.knightreasures.utilities;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Utility class containing methods for entity movement and collision handling.
 */
public final class HelpMethods {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private HelpMethods() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Determines the x-coordinate position next to a wall when an entity collides.
     *
     * @param hitbox the entity's hitbox.
     * @param xSpeed the speed of the entity along the x-axis.
     * @return the corrected x-coordinate next to the wall.
     */
    public static float getEntityXPosNextToWall(final Rectangle2D.Float hitbox, final float xSpeed) {
        final int currentTile = (int) (hitbox.x / Window.TILES_SIZE);
        if (xSpeed > 0) {
            final int tileXPos = currentTile * Window.TILES_SIZE;
            final int xOffset = (int) (Window.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - LevelsValues.FLOOR_OFFSET;
        } else {
            return currentTile * Window.TILES_SIZE;
        }
    }

    /**
     * Determines the y-coordinate position next to a wall when an entity collides.
     *
     * @param hitbox   the entity's hitbox.
     * @param airSpeed the vertical speed of the entity.
     * @return the corrected y-coordinate next to the wall.
     */
    public static float getEntityYPosNextToWall(final Rectangle2D.Float hitbox, final float airSpeed) {
        final int currentTile = (int) (hitbox.y / Window.TILES_SIZE);
        if (airSpeed > 0) {
            final int tileYPos = currentTile * Window.TILES_SIZE;
            final int yOffset = (int) (Window.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - LevelsValues.FLOOR_OFFSET;
        } else {
            return currentTile * Window.TILES_SIZE;
        }
    }

    /**
     * Checks if an entity can move to the specified position based on collision data.
     *
     * @param x       the x-coordinate to check.
     * @param y       the y-coordinate to check.
     * @param width   the width of the entity.
     * @param height  the height of the entity.
     * @param lvlData the level data containing collision information.
     * @return true if the entity can move to the position, false otherwise.
     */
    public static boolean canMoveHere(final float x, final float y, final float width,
                                      final float height, final int[][] lvlData) {
        return !isSolid(x, y, lvlData)
                && !isSolid(x + width, y + height, lvlData)
                && !isSolid(x + width, y, lvlData)
                && !isSolid(x, y + height, lvlData);
    }

    /**
     * Checks if the given position is solid (obstacle).
     *
     * @param x       the x-coordinate to check.
     * @param y       the y-coordinate to check.
     * @param lvlData the level data containing collision information.
     * @return true if the position is solid, false otherwise.
     */
    private static boolean isSolid(final float x, final float y, final int[][] lvlData) {
        final int maxWidth = lvlData[0].length * Window.TILES_SIZE;
        if (x < 0 || x >= maxWidth || y < 0 || y >= Window.GAME_HEIGHT) {
            return true;
        }

        final int xIndex = (int) (x / Window.TILES_SIZE);
        final int yIndex = (int) (y / Window.TILES_SIZE);

        return isTileSolid(xIndex, yIndex, lvlData);
    }

    /**
     * Checks if a tile is solid based on its value.
     *
     * @param xTile   the x-index of the tile.
     * @param yTile   the y-index of the tile.
     * @param lvlData the level data containing collision information.
     * @return true if the tile is solid, false otherwise.
     */
    public static boolean isTileSolid(final int xTile, final int yTile, final int[][] lvlData) {
        final int value = lvlData[yTile][xTile];
        return (value >= LevelsValues.TILE_1_NOT_SOLID)
                || (value < LevelsValues.TILE_2_NOT_SOLID)
                || (value != LevelsValues.TILE_3_NOT_SOLID);
    }

    /**
     * Checks if the entity is standing on the floor.
     *
     * @param hitbox  the entity's hitbox.
     * @param lvlData the level data containing collision information.
     * @return true if the entity is on the floor, false otherwise.
     */
    public static boolean isEntityOnFloor(final Rectangle2D.Float hitbox, final int[][] lvlData) {
        return isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)
                || isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
    }

    /**
     * Checks if the specified x position is a floor tile.
     *
     * @param hitbox  the entity's hitbox.
     * @param xSpeed  the x-speed of the entity.
     * @param lvlData the level data containing collision information.
     * @return true if the tile is a floor tile, false otherwise.
     */
    public static boolean isFloor(final Rectangle2D.Float hitbox, final float xSpeed, final int[][] lvlData) {
        return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + LevelsValues.FLOOR_OFFSET, lvlData);
    }

    /**
     * Checks if all tiles between xStart and xEnd at height y are walkable.
     *
     * @param xStart  the starting x-tile index.
     * @param xEnd    the ending x-tile index.
     * @param y       the y-tile index.
     * @param lvlData the level data containing collision information.
     * @return true if all tiles are walkable, false otherwise.
     */
    public static boolean isAllTileWalkable(final int xStart, final int xEnd, final int y, final int[][] lvlData) {
        for (int i = 0; i < xEnd - xStart; i++) {
            if (isTileSolid(xStart + i, y, lvlData)) {
                return false;
            }
            if (!isTileSolid(xStart + i, y + LevelsValues.FLOOR_OFFSET, lvlData)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if there is a clear sight between two hitboxes.
     *
     * @param lvlData       the level data containing collision information.
     * @param firstHitbox   the first entity's hitbox.
     * @param secondHitbox  the second entity's hitbox.
     * @param yTile         the y-tile index.
     * @return true if sight is clear, false otherwise.
     */
    public static boolean isSightClear(final int[][] lvlData, final Rectangle2D.Float firstHitbox,
                                       final Rectangle2D.Float secondHitbox, final int yTile) {
        final int firstXTile = (int) (firstHitbox.x / Window.TILES_SIZE);
        final int secondXTile;

        if (isSolid(secondHitbox.x, secondHitbox.y + secondHitbox.height + LevelsValues.FLOOR_OFFSET, lvlData)) {
            secondXTile = (int) (secondHitbox.x / Window.TILES_SIZE);
        } else {
            secondXTile = (int) ((secondHitbox.x + secondHitbox.width) / Window.TILES_SIZE);
        }

        if (firstXTile > secondXTile) {
            return isAllTileWalkable(secondXTile, firstXTile, yTile, lvlData);
        } else {
            return isAllTileWalkable(firstXTile, secondXTile, yTile, lvlData);
        }
    }

    public static int[][] createLevel(BufferedImage img) {
        int[][] level = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= LevelsValues.LEVEL) {
                    value = 0;
                }
                level[j][i] = value;
            }
        }
        return level;
    }
}
