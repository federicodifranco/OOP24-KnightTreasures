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
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.LvlCompletedButtons;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

public class LvlCompleted implements View{
    private final Gameplay playing;
    private final LevelManager level;
    private final ApplicationImpl game;
    private ResumeRestartHomeButtons home, next;
    private BufferedImage lvlCompletedImg;
    private int lvlCompletedX, lvlCompletedY, lvlCompletedW, lvlCompletedH;

    public LvlCompleted(Gameplay playing, LevelManager level, ApplicationImpl game) {
        this.playing = playing;
        this.game = game;
        this.level = level;
        initlvlCompletedImg();
        initButtons();
    }

    private void initlvlCompletedImg() {
        lvlCompletedImg = ResourceFuncUtilities.loadSources(Images.LVL_COMPLETE);
        lvlCompletedW = (int) (lvlCompletedImg.getWidth() * Window.SCALE);
        lvlCompletedH = (int) (lvlCompletedImg.getHeight() * Window.SCALE);
        lvlCompletedX = Window.GAME_WIDTH / 2 - lvlCompletedW / 2;
        lvlCompletedY = PanelSize.LVL_COMPLETED_Y;
    }

    private void initButtons() {
        next = new ResumeRestartHomeButtons(LvlCompletedButtons.NEXT_X, LvlCompletedButtons.BTN_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.FIRST_ROW_INDEX);
        home = new ResumeRestartHomeButtons(LvlCompletedButtons.HOME_X, LvlCompletedButtons.BTN_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.THIRD_ROW_INDEX);
    }

    private boolean isIn(ResumeRestartHomeButtons b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void update() {
        next.update();
        home.update();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, LevelsValues.GREY_BACKGROUND));
        g.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
        g.drawImage(lvlCompletedImg, lvlCompletedX, lvlCompletedY, lvlCompletedW, lvlCompletedH, null);
        next.draw(g);
        home.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(home, e)) {
            home.setMousePressed(true); 
        }else if (isIn(next, e)) {
            next.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(home, e)) {
            if (home.isMousePressed()) {
                Gamestate.setState(Gamestate.MENU);
                game.getAudioUtilities().playMenuSong();
                playing.resetAll();
                playing.getPlayer().setSpawn(level.getCurrentLevel().getPlayerSpawn());
            }
        } else if (isIn(next, e)) {
            if (next.isMousePressed()) {
                playing.loadNextLvl();
            }
        }
        home.resetBools();
        next.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        next.setMouseOver(false);
        home.setMouseOver(false);

        if (isIn(home, e)) {
            home.setMouseOver(true); 
        }else if (isIn(next, e)) {
            next.setMouseOver(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
