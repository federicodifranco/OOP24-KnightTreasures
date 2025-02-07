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

public class StarsImpl implements Stars {

    private BufferedImage fullStar, emptyStar, stars;
    private final int x, y, starSize;
    private final boolean[] starStates = new boolean[LevelsValues.NUM_STARS];

    public StarsImpl(int x, int y, int starSize) {
        this.x = x;
        this.y = y;
        this.starSize = starSize;
        loadStarImages();
    }

    private void loadStarImages() {
        stars = ResourceFuncUtilities.loadSources(Images.STARS);
        int starWidth = stars.getWidth() / 2;
        int starHeight = stars.getHeight();
        fullStar = stars.getSubimage(0, 0, starWidth, starHeight);
        emptyStar = stars.getSubimage(starWidth, 0, starWidth, starHeight);
    }

    @Override
    public void updateStarStates(boolean enemiesInactive, int playerLives, int collectedTreasure) {
        starStates[LevelsValues.FIRST_STARS] = enemiesInactive;
        starStates[LevelsValues.SECOND_STARS] = playerLives == PlayerValues.NUM_LIVES;
        starStates[LevelsValues.THIRD_STARS] = collectedTreasure == ObjectsValues.TOT_TREASURES;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < LevelsValues.NUM_STARS; i++) {
            int xPos = x + i * (starSize + Star.SPACING);
            g.drawImage(starStates[i] ? fullStar : emptyStar, xPos, y, starSize, starSize, null);
        }
    }
}
