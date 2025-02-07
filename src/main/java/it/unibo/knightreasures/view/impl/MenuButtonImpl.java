package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Buttons;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.view.api.MenuButton;

/**
 * Represents a menu button in the game's UI.
 * Handles rendering, state changes, and mouse interactions.
 */
public final class MenuButtonImpl implements MenuButton {

    /** X coordinate of the button. */
    private final int xPos;

    /** Y coordinate of the button. */
    private final int yPos;

    /** Row index in the sprite sheet. */
    private final int rowIndex;

    /** The game state associated with this button. */
    private final Gamestate gameState;

    /** The array of button images for different states. */
    private final BufferedImage[] imgs;

    /** The bounding box for mouse interaction. */
    private final Rectangle bounds;

    /** The current index of the button state (default, hover, pressed). */
    private int index;

    /** Whether the mouse is hovering over the button. */
    private boolean mouseOver;

    /** Whether the button is pressed. */
    private boolean mousePressed;

    /**
     * Creates a new MenuButton.
     *
     * @param xPos      the X coordinate of the button.
     * @param yPos      the Y coordinate of the button.
     * @param rowIndex  the row index in the sprite sheet.
     * @param gameState the game state this button represents.
     */
    public MenuButtonImpl(final int xPos, final int yPos, final int rowIndex, final Gamestate gameState) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.gameState = gameState;
        this.imgs = new BufferedImage[3];
        loadImgs();
        bounds = new Rectangle(xPos - Buttons.X_OFFSET_CENTER, yPos, Buttons.BUTTON_WIDTH, Buttons.BUTTON_HEIGHT);
    }

    /**
     * Loads the button images from the sprite sheet.
     */
    private void loadImgs() {
        final BufferedImage temp = ResourceFuncUtilities.loadSources(Images.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(
                    i * Buttons.BUTTON_WIDTH_DEFAULT,
                    rowIndex * Buttons.BUTTON_HEIGHT_DEFAULT,
                    Buttons.BUTTON_WIDTH_DEFAULT,
                    Buttons.BUTTON_HEIGHT_DEFAULT
            );
        }
    }

    /**
     * Draws the button on the screen.
     *
     * @param g the graphics object used for rendering.
     */
   @Override
    public void draw(final Graphics g) {
        g.drawImage(
                imgs[index],
                xPos - Buttons.X_OFFSET_CENTER,
                yPos,
                Buttons.BUTTON_WIDTH,
                Buttons.BUTTON_HEIGHT,
                null
        );
    }

    /**
     * Updates the button state based on mouse interactions.
     */
   @Override
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    /**
     * Checks if the mouse is hovering over the button.
     *
     * @return true if the mouse is over the button, false otherwise.
     */
   @Override
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Sets the mouse hover state.
     *
     * @param mouseOver true if the mouse is over the button.
     */
   @Override
    public void setMouseOver(final boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Checks if the button is pressed.
     *
     * @return true if the button is pressed, false otherwise.
     */
   @Override
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Sets the mouse pressed state.
     *
     * @param mousePressed true if the button is pressed.
     */
   @Override
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Gets the bounding box of the button.
     *
     * @return the button's bounding box.
     */
   @Override
    public Rectangle getBounds() {
        return new Rectangle(bounds);
    }

    /**
     * Applies the game state associated with this button.
     */
   @Override
    public void applyGameState() {
        Gamestate.setState(gameState);
    }

    /**
     * Resets the button states (mouse over and pressed).
     */
   @Override
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
