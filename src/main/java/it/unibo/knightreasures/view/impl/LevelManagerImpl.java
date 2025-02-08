package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.LevelManager;

/**
 * Manages the levels in the game, including rendering and loading level assets.
 */
public final class LevelManagerImpl implements LevelManager {

    private final ApplicationImpl game;
    private BufferedImage[] levelSprite;
    private final List<LevelImpl> levels;
    private int lvlIndex = LevelsValues.LVL_INDEX;

    /**
     * Constructs a new LevelManager.
     *
     * @param game the game application instance.
     */
    public LevelManagerImpl(final ApplicationImpl game) {
        this.game = game;
        importOutsideSprite();
        this.levels = new ArrayList<>();
        buildAllLevels();
    }

    /**
     * Loads and imports external level sprites.
     */
    private void importOutsideSprite() {
        final BufferedImage img = ResourceFuncUtilities.loadSources(Images.ENVIRONMENT);
        levelSprite = new BufferedImage[LevelsValues.SPRITES];

        for (int j = 0; j < LevelsValues.SPRITES_ROWS; j++) {
            for (int i = 0; i < LevelsValues.SPRITES_COLUMNS; i++) {
                final int index = j * LevelsValues.SPRITES_COLUMNS + i;
                levelSprite[index] = img.getSubimage(
                        i * LevelsValues.SPRITES_SIZE,
                        j * LevelsValues.SPRITES_SIZE,
                        LevelsValues.SPRITES_SIZE,
                        LevelsValues.SPRITES_SIZE
                );
            }
        }
    }

    /**
     * Method that creates all levels and added in the list.
     */
    private void buildAllLevels() {
        BufferedImage[] allLvl = ResourceFuncUtilities.getAllLevels();
        for (BufferedImage img : allLvl) {
            levels.add(new LevelImpl(img));
        }
    }

   @Override
    public void draw(final Graphics g, final int lvlOffset) {
        for (int j = 0; j < Window.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[LevelsValues.LVL_DATA_INDEX].length; i++) {
                final int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index],
                        Window.TILES_SIZE * i - lvlOffset,
                        Window.TILES_SIZE * j,
                        Window.TILES_SIZE,
                        Window.TILES_SIZE,
                        null
                );
            }
        }
    }

    @Override
    public void loadNextLvl() {
        lvlIndex++;
        if (lvlIndex >= levels.size()) {
            lvlIndex = LevelsValues.LVL_INDEX;
            Gamestate.setState(Gamestate.MENU);
            game.getAudioUtilities().playMenuSong();
        }
        LevelImpl newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().addEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    @Override
    public void update() {
        // Future level updates will go here.
    }

   @Override
    public LevelImpl getCurrentLevel() {
        return this.levels.get(lvlIndex);
    }

    @Override
    public int getAmountOfLvls() {
        return levels.size();
    }

    @Override
    public int getLevelIndex() {
        return this.lvlIndex;
    }
}
