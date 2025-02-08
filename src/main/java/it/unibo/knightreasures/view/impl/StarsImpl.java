package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Star;
import it.unibo.knightreasures.view.api.Stars;

/**
 * Implements the Stars interface to manage the display of stars indicating
 * level completion achievements.
 */
public class StarsImpl implements Stars {

    private BufferedImage fullStar, emptyStar, stars;
    private final int x, y, starSize;
    private final boolean[] starStates = new boolean[LevelsValues.NUM_STARS];

    /**
     * Constructs a StarsImpl instance.
     *
     * @param x the x-coordinate where the stars are displayed.
     * @param y the y-coordinate where the stars are displayed.
     * @param starSize the size of each star.
     */
    public StarsImpl(final int x, final int y, final int starSize) {
        this.x = x;
        this.y = y;
        this.starSize = starSize;
        loadStarImages();
    }

    /**
     * Loads star images (full and empty) from the sprite sheet.
     */
    private void loadStarImages() {
        stars = ResourceFuncUtilities.loadSources(Images.STARS);
        int starWidth = stars.getWidth() / 2;
        int starHeight = stars.getHeight();
        fullStar = stars.getSubimage(0, 0, starWidth, starHeight);
        emptyStar = stars.getSubimage(starWidth, 0, starWidth, starHeight);
    }

    /**
     * Updates the state of each star based on level achievements.
     *
     * @param enemiesInactive true if all enemies are inactive, false otherwise.
     * @param playerLives the number of lives the player has at level
     * completion.
     * @param collectedTreasure the number of treasures collected by the player.
     */
    @Override
    public void updateStarStates(final boolean enemiesInactive, final int playerLives, final int collectedTreasure) {
        starStates[LevelsValues.FIRST_STARS] = enemiesInactive;
        starStates[LevelsValues.SECOND_STARS] = playerLives == PlayerValues.NUM_LIVES;
        starStates[LevelsValues.THIRD_STARS] = collectedTreasure == ObjectsValues.TOT_TREASURES;
    }

     /**
     * Draws the stars on the screen, displaying either a full or empty star
     * depending on whether the respective achievement was met.
     *
     * @param g the Graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        for (int i = 0; i < LevelsValues.NUM_STARS; i++) {
            int xPos = x + i * (starSize + Star.SPACING);
            g.drawImage(starStates[i] ? fullStar : emptyStar, xPos, y, starSize, starSize, null);
        }
    }
}
