package it.unibo.knightreasures.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.impl.Gameplay;
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

public class GameOver implements View {

    private final Gameplay playing;
    private final LevelManager level;
    private final ApplicationImpl game;
    private BufferedImage gameoverImg;
    private int gameoverX, gameoverY, gameoverW, gameoverH;
    private ResumeRestartHomeButtons home, restart;

    public GameOver(Gameplay playing, LevelManager levelEtity, ApplicationImpl game) {
        this.playing = playing;
        this.level = levelEtity;
        this.game = game;
        createdgameover();
        createButtons();
    }

    private void createdgameover() {
        gameoverImg = ResourceFuncUtilities.loadSources(Images.GAMEOVER_PANEL);
        gameoverW = (int) (gameoverImg.getWidth() * Window.SCALE);
        gameoverH = (int) (gameoverImg.getHeight() * Window.SCALE);
        gameoverX = Window.GAME_WIDTH / 2 - gameoverW / 2;
        gameoverY = PanelSize.GAMEOVER_Y;
    }

    private void createButtons() {
        restart = new ResumeRestartHomeButtons(GameOverButtons.RESTART_X, GameOverButtons.BTN_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.SECOND_ROW_INDEX);
        home = new ResumeRestartHomeButtons(GameOverButtons.HOME_X, GameOverButtons.BTN_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.THIRD_ROW_INDEX);
    }

    private boolean isIn(ResumeRestartHomeButtons b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, LevelsValues.GREY_BACKGROUND));
        g.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
        g.drawImage(gameoverImg, gameoverX, gameoverY, gameoverW, gameoverH, null);
        restart.draw(g);
        home.draw(g);
    }

    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //playing.resetAll();
            Gamestate.setState(Gamestate.MENU);
        }
    }

    public void update() {
        restart.update();
        home.update();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        restart.setMouseOver(false);
        home.setMouseOver(false);

        if (isIn(home, e)) {
            home.setMouseOver(true);
        } else if (isIn(restart, e)) {
            restart.setMouseOver(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(home, e)) {
            if (home.isMousePressed()) {
                //playing.resetAll();
                game.getAudioUtilities().playMenuSong();
                Gamestate.setState(Gamestate.MENU);
                //playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
                game.getAudioUtilities().playMenuSong();
            }
        } else if (isIn(restart, e)) {
            if (restart.isMousePressed()) {
                //playing.resetAll();
                //playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
                game.getAudioUtilities().playLevelSong();
            }
        }

        home.resetBools();
        restart.resetBools();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(home, e)) {
            home.setMousePressed(true);
        } else if (isIn(restart, e)) {
            restart.setMousePressed(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
