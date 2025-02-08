package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

/**
 * Represents the pause menu in the game.
 * It allows resuming, restarting, or returning to the home menu.
 */
public final class Pause implements View {

    private final GameplayImpl playing;
    private final LevelManagerImpl level;
    private final ApplicationImpl game;
    private final Audio audio;
    private final ResumeRestartHomeButtonsImpl[] resumeRestartHomeButtonsBtns = 
            new ResumeRestartHomeButtonsImpl[ButtonsValues.RRH_NUM_BUTTONS];
    private BufferedImage pauseImg;
    private int pauseX, pauseY, pauseW, pauseH;

    /**
     * Constructs the pause menu.
     *
     * @param playing the current game session.
     * @param level the level manager.
     * @param game the main application instance.
     */
    public Pause(final GameplayImpl playing, final LevelManagerImpl level, final ApplicationImpl game) {
        this.playing = playing;
        this.level = level;
        this.game = game;
        this.audio = this.playing.getGame().getAudio();
        loadBackground();
        createdRRHButtons();
    }

    private void createdRRHButtons() {
        resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON] = 
                new ResumeRestartHomeButtonsImpl(RRHButtons.RESUME_X, RRHButtons.RRH_Y, 
                RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.FIRST_ROW_INDEX);
        resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON] = 
                new ResumeRestartHomeButtonsImpl(RRHButtons.RESTART_X, RRHButtons.RRH_Y, 
                RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.SECOND_ROW_INDEX);
        resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON] = 
                new ResumeRestartHomeButtonsImpl(RRHButtons.HOME_X, RRHButtons.RRH_Y, 
                RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.THIRD_ROW_INDEX);
    }

    private void loadBackground() {
        pauseImg = ResourceFuncUtilities.loadSources(Images.PAUSE_PANEL);
        pauseW = (int) (pauseImg.getWidth() * Window.SCALE);
        pauseH = (int) (pauseImg.getHeight() * Window.SCALE);
        pauseX = Window.GAME_WIDTH / 2 - pauseW / 2;
        pauseY = PanelSize.PAUSE_Y;
    }

    private boolean isIn(final MouseEvent e, final ResumeRestartHomeButtonsImpl rrh) {
        return rrh.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void update() {
        for (final ResumeRestartHomeButtonsImpl rrh : resumeRestartHomeButtonsBtns) {
            rrh.update();
        }
        this.audio.update();
    }

    @Override
    public void draw(final Graphics g) {
        g.drawImage(pauseImg, pauseX, pauseY, pauseW, pauseH, null);
        for (final ResumeRestartHomeButtonsImpl rrh : resumeRestartHomeButtonsBtns) {
            rrh.draw(g);
        }
        audio.draw(g);
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON].setMousePressed(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON].setMousePressed(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON].setMousePressed(true);
        } else {
            audio.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON])) {
            playing.unpauseGame();
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON])) {
            playing.resetAll();
            playing.unpauseGame();
            playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
            game.getAudioUtilities().playLevelSong();
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON])) {
            Gamestate.setState(Gamestate.MENU);
            game.getAudioUtilities().playMenuSong();
            playing.resetAll();
            playing.unpauseGame();
            playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
        } else {
            audio.mouseReleased(e);
        }

        for (final ResumeRestartHomeButtonsImpl rrh : resumeRestartHomeButtonsBtns) {
            rrh.resetBools();
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        for (final ResumeRestartHomeButtonsImpl rrh : resumeRestartHomeButtonsBtns) {
            rrh.setMouseOver(false);
        }

        if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON].setMouseOver(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON].setMouseOver(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON].setMouseOver(true);
        } else {
            audio.mouseMoved(e);
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
