package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Heart;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.view.api.Hearts;

/**
 * Implements the Hearts interface to manage the player's hearts display.
 * It handles heart rendering and updates based on the player's health.
 */
public class HeartsImpl implements Hearts {

    private BufferedImage fullHeart, emptyHeart;
    private final int x, y, heartSize;
    private int currentHearts;

    /**
     * Constructs the HeartsImpl instance with the given position.
     *
     * @param x the x-coordinate where the hearts are displayed.
     * @param y the y-coordinate where the hearts are displayed.
     */
    public HeartsImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.currentHearts = PlayerValues.NUM_LIVES;
        this.heartSize = Heart.HEART_SIZE;
        loadHeartImages();
    }

    /**
     * Loads heart images (full and empty) from the sprite sheet.
     */
    private void loadHeartImages() {
        final BufferedImage heartsImgs = ResourceFuncUtilities.loadSources(Images.HEARTS);
        final int heartWidth = heartsImgs.getWidth() / 2;
        final int heartHeight = heartsImgs.getHeight();
        fullHeart = heartsImgs.getSubimage(0, 0, heartWidth, heartHeight);
        emptyHeart = heartsImgs.getSubimage(heartWidth, 0, heartWidth, heartHeight);
    }

    /**
     * Sets the number of hearts the player currently has.
     * Ensures that the value is between 0 and the maximum allowed lives.
     *
     * @param currentHearts the new number of hearts.
     */
    @Override
    public void setCurrentHearts(final int currentHearts) {
        this.currentHearts = Math.max(0, Math.min(currentHearts, PlayerValues.NUM_LIVES));
    }

    /**
     * Gets the number of hearts the player currently has.
     *
     * @return the current number of hearts.
     */
    @Override
    public int getCurrentHearts() {
        return currentHearts;
    }

    /**
     * Draws the player's hearts on the screen.
     * The hearts are displayed in a row, with full hearts representing health
     * and empty hearts representing lost health.
     *
     * @param g the Graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        for (int i = 0; i < PlayerValues.NUM_LIVES; i++) {
            final int xPos = x + i * (heartSize + Heart.SPACING);
            g.drawImage(i < currentHearts ? fullHeart : emptyHeart, xPos, y, heartSize, heartSize, null);
        }
    }
}
