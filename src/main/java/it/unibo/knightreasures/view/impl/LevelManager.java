package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

public class LevelManager {

    private ApplicationImpl game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(ApplicationImpl game) {
        this.game = game;
        importOutsideSprite();
        levelOne = new Level(ResourceFuncUtilities.createLevel());
    }

    private void importOutsideSprite() {
        BufferedImage img = ResourceFuncUtilities.loadSources(Images.ENVIRONMENT);
        levelSprite = new BufferedImage[LevelsValues.SPRITES];
        for (int j = 0; j < LevelsValues.SPRITES_ROWS; j++) {
            for (int i = 0; i < LevelsValues.SPRITES_COLUMNS; i++) {
                int index = j*LevelsValues.SPRITES_COLUMNS + i;
                levelSprite[index] = img.getSubimage(i*LevelsValues.SPRITES_SIZE, j*LevelsValues.SPRITES_SIZE, LevelsValues.SPRITES_SIZE, LevelsValues.SPRITES_SIZE);
            }
        }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < Window.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Window.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Window.TILES_SIZE*i, Window.TILES_SIZE*j, Window.TILES_SIZE, Window.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }
}
