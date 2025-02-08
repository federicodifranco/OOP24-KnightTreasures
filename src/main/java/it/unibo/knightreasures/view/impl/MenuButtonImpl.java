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

    private final int xPos, yPos, rowIndex;
    private final Gamestate gameState;
    private final BufferedImage[] imgs;
    private final Rectangle bounds;
    private int index;
    private boolean mouseOver, mousePressed;

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

   @Override
    public boolean isMouseOver() {
        return mouseOver;
    }

   @Override
    public void setMouseOver(final boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

   @Override
    public boolean isMousePressed() {
        return mousePressed;
    }

   @Override
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

   @Override
    public Rectangle getBounds() {
        return new Rectangle(bounds);
    }

   @Override
    public void applyGameState() {
        Gamestate.setState(gameState);
    }

   @Override
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
