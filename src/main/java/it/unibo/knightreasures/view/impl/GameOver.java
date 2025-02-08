package it.unibo.knightreasures.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.GameOverButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

/**
 * Represents the Game Over screen.
 * Displays the player's options to restart or return to the main menu.
 */
public final class GameOver implements View {

    private final GameplayImpl playing;
    private final LevelManagerImpl level;
    private final ApplicationImpl game;
    private BufferedImage gameoverImg;
    private int gameoverX, gameoverY, gameoverW, gameoverH;
    private ResumeRestartHomeButtonsImpl home;
    private ResumeRestartHomeButtonsImpl restart;

    /**
     * Constructs the Game Over screen.
     *
     * @param playing The current gameplay instance.
     * @param level   The level manager handling the current level.
     * @param game    The main application instance.
     */
    public GameOver(final GameplayImpl playing, final LevelManagerImpl level, final ApplicationImpl game) {
        this.playing = playing;
        this.level = level;
        this.game = game;
        createGameOverPanel();
        createButtons();
    }

    /**
     * Loads and initializes the Game Over panel image.
     */
    private void createGameOverPanel() {
        gameoverImg = ResourceFuncUtilities.loadSources(Images.GAMEOVER_PANEL);
        gameoverW = (int) (gameoverImg.getWidth() * Window.SCALE);
        gameoverH = (int) (gameoverImg.getHeight() * Window.SCALE);
        gameoverX = Window.GAME_WIDTH / 2 - gameoverW / 2;
        gameoverY = PanelSize.GAMEOVER_Y;
    }

    /**
     * Initializes the buttons for restart and returning home.
     */
    private void createButtons() {
        restart = new ResumeRestartHomeButtonsImpl(
                GameOverButtons.RESTART_X, 
                GameOverButtons.BTN_Y, 
                RRHButtons.RRH_SIZE, 
                RRHButtons.RRH_SIZE, 
                ButtonsValues.SECOND_ROW_INDEX);
        home = new ResumeRestartHomeButtonsImpl(
                GameOverButtons.HOME_X, 
                GameOverButtons.BTN_Y, 
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
    public void draw(final Graphics g) {
        g.setColor(new Color(0, 0, 0, LevelsValues.GREY_BACKGROUND));
        g.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
        g.drawImage(gameoverImg, gameoverX, gameoverY, gameoverW, gameoverH, null);
        restart.draw(g);
        home.draw(g);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.setState(Gamestate.MENU);
        }
    }

    @Override
    public void update() {
        restart.update();
        home.update();
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        restart.setMouseOver(false);
        home.setMouseOver(false);

        if (isIn(home, e)) {
            home.setMouseOver(true);
        } else if (isIn(restart, e)) {
            restart.setMouseOver(true);
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isIn(home, e) && home.isMousePressed()) {
            playing.resetAll();
            game.getAudioUtilities().playMenuSong();
            Gamestate.setState(Gamestate.MENU);
            playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
            game.getAudioUtilities().playMenuSong();
        } else if (isIn(restart, e) && restart.isMousePressed()) {
            playing.resetAll();
            playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
            game.getAudioUtilities().playLevelSong();
        }

        home.resetBools();
        restart.resetBools();
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (isIn(home, e)) {
            home.setMousePressed(true);
        } else if (isIn(restart, e)) {
            restart.setMousePressed(true);
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // No action required on click
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        // No action required for key release
    }
}
