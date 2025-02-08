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
import it.unibo.knightreasures.utilities.ViewConstants.PanelSize;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.utilities.ViewConstants.SettingsButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;

/**
 * The {@code Settings} class represents the settings menu of the game,
 * allowing users to modify audio settings and return to the main menu.
 */
public class Settings extends State implements View {

    private final Audio audio;
    private final ResumeRestartHomeButtonsImpl[] homeButton = new ResumeRestartHomeButtonsImpl[ButtonsValues.RRH_NUM_BUTTONS];
    private BufferedImage homeBackgroundImg, settingsImg;
    private int settingsWidth, settingsHeight, settingsX, settingsY;

    /**
     * Constructs the settings menu.
     *
     * @param game The main application instance.
     */
    public Settings(final ApplicationImpl game) {
        super(game);
        loadSettingsPanel();
        loadBackground();
        createHomeButton();
        audio = game.getAudio();
    }

    /**
     * Loads the settings panel image and its dimensions.
     */
    private void loadSettingsPanel() {
        settingsImg = ResourceFuncUtilities.loadSources(Images.SETTINGS_PANEL);
        settingsWidth = (int) (settingsImg.getWidth() * Window.SCALE);
        settingsHeight = (int) (settingsImg.getHeight() * Window.SCALE);
        settingsX = Window.GAME_WIDTH / 2 - settingsWidth / 2;
        settingsY = PanelSize.SETTINGS_Y;
    }

    /**
     * Loads the background image of the settings menu.
     */
    private void loadBackground() {
        homeBackgroundImg = ResourceFuncUtilities.loadSources(Images.HOME_BACKGROUND);
    }

    /**
     * Creates the home button for returning to the main menu.
     */
    private void createHomeButton() {
        homeButton[ButtonsValues.HOME_BUTTON] = new ResumeRestartHomeButtonsImpl(
            SettingsButtons.HOME_X, 
            SettingsButtons.HOME_Y, 
            RRHButtons.RRH_SIZE, 
            RRHButtons.RRH_SIZE, 
            ButtonsValues.THIRD_ROW_INDEX
        );
    }

    /**
     * Checks if the given mouse event occurred within the bounds of a button.
     *
     * @param e The mouse event.
     * @param h The button to check.
     * @return {@code true} if the mouse event is inside the button's bounds, otherwise {@code false}.
     */
    private boolean isIn(final MouseEvent e, final ResumeRestartHomeButtonsImpl h) {
        return h.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Updates the settings menu, including the audio settings and home button state.
     */
    @Override
    public void update() {
        audio.update();
        homeButton[ButtonsValues.HOME_BUTTON].update();
    }

    /**
     * Draws the settings menu, including the background, settings panel, audio controls, and home button.
     *
     * @param g The graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        g.drawImage(homeBackgroundImg, 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(settingsImg, settingsX, settingsY, settingsWidth, settingsHeight, null);
        audio.draw(g);
        homeButton[ButtonsValues.HOME_BUTTON].draw(g);
    }

    /**
     * Handles mouse press events. Detects if the user clicks on the home button or interacts with audio settings.
     *
     * @param e The mouse event containing the coordinates of the mouse press.
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        if (isIn(e, homeButton[ButtonsValues.HOME_BUTTON])) {
            homeButton[ButtonsValues.HOME_BUTTON].setMousePressed(true);
        } else {
            audio.mousePressed(e);
        }
    }

    /**
     * Handles mouse release events. If the home button was pressed, it navigates back to the main menu.
     *
     * @param e The mouse event containing the coordinates of the mouse release.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isIn(e, homeButton[ButtonsValues.HOME_BUTTON])) {
            if (homeButton[ButtonsValues.HOME_BUTTON].isMousePressed()) {
                Gamestate.setState(Gamestate.MENU);
            }
        } else {
            audio.mouseReleased(e);
        }
        homeButton[ButtonsValues.HOME_BUTTON].resetBools();
    }

    /**
     * Handles mouse movement events. Updates the hover state of the home button.
     *
     * @param e The mouse event containing the coordinates of the mouse movement.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        homeButton[ButtonsValues.HOME_BUTTON].setMouseOver(false);
        if (isIn(e, homeButton[ButtonsValues.HOME_BUTTON])) {
            homeButton[ButtonsValues.HOME_BUTTON].setMouseOver(true);
        } else {
            audio.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }
}
