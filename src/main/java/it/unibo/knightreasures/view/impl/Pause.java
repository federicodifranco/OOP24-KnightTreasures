package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.impl.Gameplay;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

public class Pause implements View {

    private final Gameplay playing;
    private final LevelManager level;
    private final ApplicationImpl game;
    private final Audio audio;
    private final ResumeRestartHomeButtons[] resumeRestartHomeButtonsBtns = new ResumeRestartHomeButtons[ButtonsValues.RRH_NUM_BUTTONS];
    private BufferedImage pauseImg;
    private int pauseX, pauseY, pauseW, pauseH;

    public Pause(Gameplay playing, LevelManager level, ApplicationImpl game) {
        this.playing = playing;
        this.level = level;
        this.game = game;
        this.audio = this.playing.getGame().getAudio();
        loadBackground();
        createdRRHButtons();
    }

    private void createdRRHButtons() {
        resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON] = new ResumeRestartHomeButtons(
                RRHButtons.RESUME_X, RRHButtons.RRH_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE,
                ButtonsValues.FIRST_ROW_INDEX);
        resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON] = new ResumeRestartHomeButtons(
                RRHButtons.RESTART_X, RRHButtons.RRH_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE,
                ButtonsValues.SECOND_ROW_INDEX);
        resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON] = new ResumeRestartHomeButtons(RRHButtons.HOME_X,
                RRHButtons.RRH_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.THIRD_ROW_INDEX);
    }

    private void loadBackground() {
        pauseImg = ResourceFuncUtilities.loadSources(Images.PAUSE_PANEL);
        pauseW = (int) (pauseImg.getWidth() * Window.SCALE);
        pauseH = (int) (pauseImg.getHeight() * Window.SCALE);
        pauseX = Window.GAME_WIDTH / 2 - pauseW / 2;
        pauseY = PanelSize.PAUSE_Y;
    }

    private boolean isIn(MouseEvent e, ResumeRestartHomeButtons rrh) {
        return rrh.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void update() {
        for (ResumeRestartHomeButtons rrh : resumeRestartHomeButtonsBtns) {
            rrh.update();
        }
        this.audio.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(pauseImg, pauseX, pauseY, pauseW, pauseH, null);
        for (ResumeRestartHomeButtons rrh : resumeRestartHomeButtonsBtns) {
            rrh.draw(g);
        }
        audio.draw(g);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON].setMousePressed(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON].setMousePressed(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON].setMousePressed(true);
        } else audio.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON])) {
            playing.unpauseGame();
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON])) {
            playing.unpauseGame();
            game.getAudioUtilities().playLevelSong();
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON])) {
            Gamestate.setState(Gamestate.MENU);
            game.getAudioUtilities().playMenuSong();
            playing.unpauseGame();
        } else audio.mouseReleased(e);

        for (ResumeRestartHomeButtons rrh : resumeRestartHomeButtonsBtns) {
            rrh.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        for (ResumeRestartHomeButtons rrh : resumeRestartHomeButtonsBtns) {
            rrh.setMouseOver(false);
        }

        if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESUME_BUTTON].setMouseOver(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.RESTART_BUTTON].setMouseOver(true);
        } else if (isIn(e, resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON])) {
            resumeRestartHomeButtonsBtns[ButtonsValues.HOME_BUTTON].setMouseOver(true);
        } else audio.mouseMoved(e);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
