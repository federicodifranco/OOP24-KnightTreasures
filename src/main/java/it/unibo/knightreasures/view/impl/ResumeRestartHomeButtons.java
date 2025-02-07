package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;

/**
 * Represents buttons for Resume, Restart, and Home in the pause menu.
 */
public final class ResumeRestartHomeButtons extends PauseButtonImpl {

    private final BufferedImage[] rrhButtonsImgs;
    private int index;
    private boolean mouseOver, mousePressed;

    /**
     * Constructs a new button for the pause menu.
     *
     * @param x the X coordinate.
     * @param y the Y coordinate.
     * @param width the button width.
     * @param height the button height.
     * @param rowIndex the index representing the button type.
     */
    public ResumeRestartHomeButtons(final int x, final int y, final int width, final int height, final int rowIndex) {
        super(x, y, width, height, rowIndex);
        this.rrhButtonsImgs = new BufferedImage[ButtonsValues.RRH_NUM_BUTTONS];
        loadImgs();
    }

    private void loadImgs() {
        final BufferedImage rrhButtonsImg = ResourceFuncUtilities.loadSources(Images.RRH_BUTTONS);
        for (int i = 0; i < rrhButtonsImgs.length; i++) {
            rrhButtonsImgs[i] = rrhButtonsImg.getSubimage(
                i * RRHButtons.RRH_SIZE_DEFAULT,
                getRowIndex() * RRHButtons.RRH_SIZE_DEFAULT,
                RRHButtons.RRH_SIZE_DEFAULT,
                RRHButtons.RRH_SIZE_DEFAULT
            );
        }
    }

    /**
     * Updates the button state based on user interactions.
     */
    public void update() {
        index = ButtonsValues.FIRST_ROW_INDEX;
        if (mouseOver) {
            index = ButtonsValues.SECOND_ROW_INDEX;
        }
        if (mousePressed) {
            index = ButtonsValues.THIRD_ROW_INDEX;
        }
    }

    /**
     * Draws the button.
     *
     * @param g the graphics object used for rendering.
     */
    public void draw(final Graphics g) {
        g.drawImage(rrhButtonsImgs[index], getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Resets the button states.
     */
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Checks if the mouse is over the button.
     *
     * @return true if the mouse is over the button, false otherwise.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Sets whether the mouse is over the button.
     *
     * @param mouseOver true if the mouse is over, false otherwise.
     */
    public void setMouseOver(final boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Checks if the button is pressed.
     *
     * @return true if the button is pressed, false otherwise.
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Sets whether the button is pressed.
     *
     * @param mousePressed true if the button is pressed, false otherwise.
     */
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
