package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.RRHButtons;

public class ResumeRestartHomeButtons extends PauseButton {

    private BufferedImage[] rrhButtonsImgs;
    private int index;
    private boolean mouseOver, mousePressed;

    public ResumeRestartHomeButtons(int x, int y, int widht, int height, int rowIndex) {
        super(x, y, widht, height, rowIndex);
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage rrhButtonsImg = ResourceFuncUtilities.loadSources(Images.RRH_BUTTONS);
        rrhButtonsImgs = new BufferedImage[ButtonsValues.RRH_NUM_BUTTONS];
        for (int i = 0; i < rrhButtonsImgs.length; i++) {
            rrhButtonsImgs[i] = rrhButtonsImg.getSubimage(i * RRHButtons.RRH_SIZE_DEFAULT, rowIndex * RRHButtons.RRH_SIZE_DEFAULT, RRHButtons.RRH_SIZE_DEFAULT, RRHButtons.RRH_SIZE_DEFAULT);
        }
    }
    public void update() {
        index = ButtonsValues.FIRST_ROW_INDEX;
        if (mouseOver) index = ButtonsValues.SECOND_ROW_INDEX;
        if (mousePressed) index = ButtonsValues.THIRD_ROW_INDEX;
    }

    public void draw(Graphics g) {
        g.drawImage(rrhButtonsImgs[index], x, y, width, height, null);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

}
