package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;
import it.unibo.knightreasures.view.api.ResumeRestartHomeButtons;

/**
 * Represents buttons for Resume, Restart, and Home in the pause menu.
 */
public final class ResumeRestartHomeButtonsImpl extends PauseButtonImpl implements ResumeRestartHomeButtons {

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
    public ResumeRestartHomeButtonsImpl(final int x, final int y, final int width, final int height, final int rowIndex) {
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

   @Override
    public void update() {
        index = ButtonsValues.FIRST_ROW_INDEX;
        if (mouseOver) {
            index = ButtonsValues.SECOND_ROW_INDEX;
        }
        if (mousePressed) {
            index = ButtonsValues.THIRD_ROW_INDEX;
        }
    }

   @Override
    public void draw(final Graphics g) {
        g.drawImage(rrhButtonsImgs[index], getX(), getY(), getWidth(), getHeight(), null);
    }

   @Override
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
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
}
