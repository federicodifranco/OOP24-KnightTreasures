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

/**
 * Represents the main menu of the game. It includes buttons to start, open
 * settings, or exit the game.
 */
public final class Menu extends State implements View {

    private final ApplicationImpl game;
    private final MenuButtonImpl[] btns = new MenuButtonImpl[ButtonsValues.MENU_NUM_BUTTONS];
    private BufferedImage menuImg, homeBackgroundImg, legendImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private int legendX, legendY, legendWidth, legendHeight;

    /**
     * Constructs a new `Menu` instance.
     *
     * @param game the game application instance.
     */
    public Menu(final ApplicationImpl game) {
        super(game);
        this.game = game;
        loadLegend();
        loadButtons();
        loadMenuPanel();
        loadHomeBackground();
    }

    /**
     * Loads the legend image and sets its dimensions.
     */
    private void loadLegend() {
        legendImg = ResourceFuncUtilities.loadSources(Images.LEGEND);
        legendWidth = (int) (legendImg.getWidth() * Window.SCALE);
        legendHeight = (int) (legendImg.getHeight() * Window.SCALE);
        legendX = (Window.GAME_WIDTH / 2) - LegendConstants.LEGEND_X_OFFSET;
        legendY = LegendConstants.LEGEND_Y;
    }

    /**
     * Loads the home background image.
     */
    private void loadHomeBackground() {
        homeBackgroundImg = ResourceFuncUtilities.loadSources(Images.HOME_BACKGROUND);
    }

    /**
     * Loads the menu panel image and sets its dimensions.
     */
    private void loadMenuPanel() {
        menuImg = ResourceFuncUtilities.loadSources(Images.MENU_PANEL);
        menuWidth = (int) (menuImg.getWidth() * Window.SCALE);
        menuHeight = (int) (menuImg.getHeight() * Window.SCALE);
        menuX = Window.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = PanelSize.MENU_Y;
    }

    /**
     * Loads the menu buttons for Play, Settings, and Quit.
     */
    private void loadButtons() {
        btns[ButtonsValues.PLAY_BUTTON] = new MenuButtonImpl(
                Window.GAME_WIDTH / 2, MenuButtons.PLAY_Y, ButtonsValues.FIRST_ROW_INDEX, Gamestate.PLAYING
        );
        btns[ButtonsValues.SETTINGS_BUTTON] = new MenuButtonImpl(
                Window.GAME_WIDTH / 2, MenuButtons.SETTINGS_Y, ButtonsValues.SECOND_ROW_INDEX, Gamestate.SETTINGS
        );
        btns[ButtonsValues.QUIT_BUTTON] = new MenuButtonImpl(
                Window.GAME_WIDTH / 2, MenuButtons.QUIT_Y, ButtonsValues.THIRD_ROW_INDEX, Gamestate.EXIT
        );
    }

    /**
     * Resets button states.
     */
    private void resetButtons() {
        for (final MenuButtonImpl mb : btns) {
            mb.resetBools();
        }
    }

    @Override
    public void update() {
        for (final MenuButtonImpl mb : btns) {
            mb.update();
        }
    }

    @Override
    public void draw(final Graphics g) {
        g.drawImage(homeBackgroundImg, 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(menuImg, menuX, menuY, menuWidth, menuHeight, null);
        g.drawImage(legendImg, legendX, legendY, legendWidth, legendHeight, null);

        for (final MenuButtonImpl mb : btns) {
            mb.draw(g);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        for (final MenuButtonImpl mb : btns) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        for (final MenuButtonImpl mb : btns) {
            if (isIn(e, mb) && mb.isMousePressed()) {
                mb.applyGameState();
            }
            if (Gamestate.getState() == Gamestate.PLAYING) {
                this.game.getAudioUtilities().playLevelSong();
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        for (final MenuButtonImpl mb : btns) {
            mb.setMouseOver(false);
        }
        for (final MenuButtonImpl mb : btns) {
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        // No key press handling yet
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        // No key release handling yet
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // No mouse click handling yet
    }
}
