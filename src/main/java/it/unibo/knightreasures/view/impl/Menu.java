package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.LegendConstants;
import it.unibo.knightreasures.utilities.ViewConstants.MenuButtons;
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;


public class Menu extends State implements View {

    private final MenuButton[] btns = new MenuButton[ButtonsValues.MENU_NUM_BUTTONS];
    private BufferedImage menuImg, homeBackgroundImg, legendImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private int legendX, legendY, legendWidth, legendHeight;

    public Menu(ApplicationImpl game) {
        super(game);
        loadLegend();
        loadButtons();
        loadMenuPanel();
        loadHomeBackground();
    }

    private void loadLegend() {
        legendImg = ResourceFuncUtilities.loadSources(Images.LEGEND);
        legendWidth = (int) (legendImg.getWidth() * Window.SCALE);
        legendHeight = (int) (legendImg.getHeight() * Window.SCALE);
        legendX = (Window.GAME_WIDTH / 2) - LegendConstants.LEGEND_X_OFFSET;
        legendY = LegendConstants.LEGEND_Y;
    }

    private void loadHomeBackground() {
        homeBackgroundImg = ResourceFuncUtilities.loadSources(Images.HOME_BACKGROUND);
    }

    private void loadMenuPanel() {
        menuImg = ResourceFuncUtilities.loadSources(Images.MENU_PANEL);
        menuWidth = (int) (menuImg.getWidth() * Window.SCALE);
        menuHeight = (int) (menuImg.getHeight() * Window.SCALE);
        menuX = Window.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = PanelSize.MENU_Y;
    }

    private void loadButtons() {
        btns[ButtonsValues.PLAY_BUTTON] = new MenuButton(Window.GAME_WIDTH / 2, MenuButtons.PLAY_Y, ButtonsValues.FIRST_ROW_INDEX, Gamestate.PLAYING);
        btns[ButtonsValues.SETTINGS_BUTTON] = new MenuButton(Window.GAME_WIDTH / 2, MenuButtons.SETTINGS_Y, ButtonsValues.SECOND_ROW_INDEX, Gamestate.SETTINGS);
        btns[ButtonsValues.QUIT_BUTTON] = new MenuButton(Window.GAME_WIDTH / 2, MenuButtons.QUIT_Y, ButtonsValues.THIRD_ROW_INDEX, Gamestate.EXIT);
    }

    private void resetButtons() {
        for (MenuButton mb : btns) {
            mb.resetBools();
        }
    }

    @Override
    public void update() {
        for (MenuButton mb : btns) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(homeBackgroundImg, 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(menuImg, menuX, menuY, menuWidth, menuHeight, null);
        g.drawImage(legendImg, legendX, legendY, legendWidth, legendHeight, null);
        for (MenuButton mb : btns) {
            mb.draw(g);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : btns) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : btns) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGameState();
                }
            }
        }
        resetButtons();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : btns) {
            mb.setMouseOver(false);
        }
        
        for (MenuButton mb : btns) {
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
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
}