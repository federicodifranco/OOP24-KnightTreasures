package it.unibo.knightreasures.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.impl.ObjectManagerImpl;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.LvlCompletedButtons;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Star;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

/**
 * Represents the level completed screen.
 * Displays the player's performance, earned stars, and provides buttons for navigation.
 */
public final class LvlCompleted implements View {

    private final GameplayImpl playing;
    private final LevelManagerImpl level;
    private final ApplicationImpl game;
    private final StarsImpl stars;
    private final ObjectManagerImpl objects;
    private boolean enemiesInactive;
    private ResumeRestartHomeButtonsImpl home;
    private ResumeRestartHomeButtonsImpl next;
    private BufferedImage lvlCompletedImg;
    private int lvlCompletedX, lvlCompletedY, lvlCompletedW, lvlCompletedH;
    private int playerLives, collectedTreasure;

    /**
     * Constructs the level completed screen.
     *
     * @param playing The current gameplay instance.
     * @param level   The level manager handling the current level.
     * @param game    The main application instance.
     */
    public LvlCompleted(final GameplayImpl playing, final LevelManagerImpl level, final ApplicationImpl game) {
        this.playing = playing;
        this.game = game;
        this.level = level;
        this.objects = playing.getObjectManager();
        this.stars = new StarsImpl(Star.INIT_X, Star.INIT_Y, Star.STAR_SIZE);
        initLvlCompletedImg();
        initButtons();
    }

    /**
     * Loads the level completed panel image and scales it accordingly.
     */
    private void initLvlCompletedImg() {
        lvlCompletedImg = ResourceFuncUtilities.loadSources(Images.LVL_COMPLETE);
        lvlCompletedW = (int) (lvlCompletedImg.getWidth() * Window.SCALE);
        lvlCompletedH = (int) (lvlCompletedImg.getHeight() * Window.SCALE);
        lvlCompletedX = Window.GAME_WIDTH / 2 - lvlCompletedW / 2;
        lvlCompletedY = PanelSize.LVL_COMPLETED_Y;
    }

    /**
     * Initializes the buttons for navigating after completing the level.
     */
    private void initButtons() {
        next = new ResumeRestartHomeButtonsImpl(
                LvlCompletedButtons.NEXT_X, 
                LvlCompletedButtons.BTN_Y, 
                RRHButtons.RRH_SIZE, 
                RRHButtons.RRH_SIZE, 
                ButtonsValues.FIRST_ROW_INDEX);
        home = new ResumeRestartHomeButtonsImpl(
                LvlCompletedButtons.HOME_X, 
                LvlCompletedButtons.BTN_Y, 
                RRHButtons.RRH_SIZE, 
                RRHButtons.RRH_SIZE, 
                ButtonsValues.THIRD_ROW_INDEX);
    }

    /**
     * Checks if a mouse event occurred inside a button's bounds.
     *
     * @param b The button to check.
     * @param e The mouse event.
     * @return True if the mouse is within the button's bounds, otherwise false.
     */
    private boolean isIn(final ResumeRestartHomeButtonsImpl b, final MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void update() {
        next.update();
        home.update();
    }

    @Override
    public void draw(final Graphics g) {
        g.setColor(new Color(0, 0, 0, LevelsValues.GREY_BACKGROUND));
        g.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
        g.drawImage(lvlCompletedImg, lvlCompletedX, lvlCompletedY, lvlCompletedW, lvlCompletedH, null);
        next.draw(g);
        home.draw(g);
        enemiesInactive = !playing.getEnemyManager().hasActiveEnemies();
        playerLives = playing.getPlayer().getLives();
        collectedTreasure = objects.isAllCollectedTreasures();
        stars.updateStarStates(enemiesInactive, playerLives, collectedTreasure);
        stars.draw(g);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // No action required on click
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (isIn(home, e)) {
            home.setMousePressed(true);
        } else if (isIn(next, e)) {
            next.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isIn(home, e) && home.isMousePressed()) {
            Gamestate.setState(Gamestate.MENU);
            game.getAudioUtilities().playMenuSong();
            playing.resetAll();
            playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
        } else if (isIn(next, e) && next.isMousePressed()) {
            playing.loadNextLvl();
        }
        home.resetBools();
        next.resetBools();
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        next.setMouseOver(false);
        home.setMouseOver(false);

        if (isIn(home, e)) {
            home.setMouseOver(true);
        } else if (isIn(next, e)) {
            next.setMouseOver(true);
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        // No action required for key press
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        // No action required for key release
    }
}
