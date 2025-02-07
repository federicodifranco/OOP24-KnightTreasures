package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Heart;
import it.unibo.knightreasures.utilities.ViewConstants.Images;

public class HeartsImpl {
    private BufferedImage fullHeart, emptyHeart, heartsImgs;
    private final int x, y, heartSize;
    private int currentHearts;

    public HeartsImpl(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentHearts =  PlayerValues.NUM_LIVES;
        this.heartSize = Heart.HEART_SIZE;
        loadHeartImages();
    }

    private void loadHeartImages() {
        heartsImgs = ResourceFuncUtilities.loadSources(Images.HEARTS);
        int heartWidth = heartsImgs.getWidth() / 2;
        int heartHeight = heartsImgs.getHeight();
        fullHeart = heartsImgs.getSubimage(0, 0, heartWidth, heartHeight);
        emptyHeart = heartsImgs.getSubimage(heartWidth, 0, heartWidth, heartHeight);
    }

   
    public void setCurrentHearts(int currentHearts) {
        this.currentHearts = Math.max(0, Math.min(currentHearts, PlayerValues.NUM_LIVES));
    }

   
    public int getCurrentHearts() {
        return currentHearts;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < PlayerValues.NUM_LIVES; i++) {
            int xPos = x + i * (heartSize + Heart.SPACING);
            g.drawImage(i < currentHearts ? fullHeart : emptyHeart, xPos, y, heartSize, heartSize, null);
        }
    }

}

