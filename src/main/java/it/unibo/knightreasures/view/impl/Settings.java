package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.SettingsButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

public class Settings extends State implements View {

    private final Audio audio;
    private final ResumeRestartHomeButtons[] homeButton = new ResumeRestartHomeButtons[ButtonsValues.RRH_NUM_BUTTONS];
    private BufferedImage homeBackgroundImg, settingsImg;
    private int settingsWidth, settingsHeight, settingsX, settingsY;

    public Settings(ApplicationImpl game) {
        super(game);
        loadSettingsPanel();
        loadBackground();
        createHomeButton();
        audio = game.getAudio();
    }

    private void loadSettingsPanel() {
        settingsImg = ResourceFuncUtilities.loadSources(Images.SETTINGS_PANEL);
        settingsWidth = (int) (settingsImg.getWidth() * Window.SCALE);
        settingsHeight = (int) (settingsImg.getHeight() * Window.SCALE);
        settingsX = Window.GAME_WIDTH / 2 - settingsWidth / 2;
        settingsY = PanelSize.SETTINGS_Y;
    }

    private void loadBackground() {
        homeBackgroundImg = ResourceFuncUtilities.loadSources(Images.HOME_BACKGROUND);
    }

    private void createHomeButton() {
        homeButton[ButtonsValues.HOME_BUTTON] = new ResumeRestartHomeButtons(SettingsButtons.HOME_X, SettingsButtons.HOME_Y, RRHButtons.RRH_SIZE, RRHButtons.RRH_SIZE, ButtonsValues.THIRD_ROW_INDEX);
    }

    @Override
    public void update() {
        audio.update();
        homeButton[ButtonsValues.HOME_BUTTON].update();
    }

    public void draw(Graphics g) {
        g.drawImage(homeBackgroundImg, 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(settingsImg, settingsX, settingsY, settingsWidth, settingsHeight, null);
        audio.draw(g);
        homeButton[ButtonsValues.HOME_BUTTON].draw(g);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, homeButton[ButtonsValues.HOME_BUTTON])) {
            homeButton[ButtonsValues.HOME_BUTTON].setMousePressed(true);
        } else audio.mousePressed(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, homeButton[ButtonsValues.HOME_BUTTON])) {
            if (homeButton[ButtonsValues.HOME_BUTTON].isMousePressed()) Gamestate.setState(Gamestate.MENU);
        } else audio.mouseReleased(e);
        homeButton[ButtonsValues.HOME_BUTTON].resetBools();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        homeButton[ButtonsValues.HOME_BUTTON].setMouseOver(false);
        if (isIn(e, homeButton[ButtonsValues.HOME_BUTTON])) {
            homeButton[ButtonsValues.HOME_BUTTON].setMouseOver(true);
        } else audio.mouseMoved(e);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    private boolean isIn(MouseEvent e, ResumeRestartHomeButtons h) {
        return h.getBounds().contains(e.getX(), e.getY());
    }

   
}
