package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;


public class Menu extends State implements View {

    private MenuButton[] btns = new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(ApplicationImpl game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = ResourceFuncUtilities.loadSources(Images.HOME_BACKGROUND);
        menuWidth = (int) (backgroundImg.getHeight() * Window.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Window.SCALE);
        menuX = Window.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Window.SCALE);
    }

    private void loadButtons() {
        btns[0] = new MenuButton(Window.GAME_WIDTH / 2, (int) (150 * Window.SCALE), 0, Gamestate.PLAYING);
        btns[1] = new MenuButton(Window.GAME_WIDTH / 2, (int) (220 * Window.SCALE), 1, Gamestate.SETTINGS);
        btns[2] = new MenuButton(Window.GAME_WIDTH / 2, (int) (290 * Window.SCALE), 2, Gamestate.EXIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : btns) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : btns) {
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : btns) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
            break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : btns) {
            if (isIn(e, mb)) {
                mb.applyGameState();
            }
            break;
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.setState(Gamestate.PLAYING);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private void resetButtons() {
        for (MenuButton mb : btns) {
            mb.resetBools();
        }
    }
}