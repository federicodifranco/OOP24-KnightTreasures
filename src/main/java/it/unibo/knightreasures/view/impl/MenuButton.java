package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Buttons;
import it.unibo.knightreasures.utilities.ViewConstants.Images;

public class MenuButton {

    private int xPos, yPos, rowIndex, index;
    private Gamestate gameState;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int xyPos, int rowIndex, Gamestate gameState) {
        this.xPos = xPos;
        this.yPos = xyPos;
        this.rowIndex = rowIndex;
        this.gameState = gameState;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - Buttons.X_OFFSET_CENTER, yPos, Buttons.BUTTON_WIDTH, Buttons.BUTTON_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = ResourceFuncUtilities.loadSources(Images.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * Buttons.BUTTON_WIDTH_DEFAULT, rowIndex * Buttons.BUTTON_HEIGHT_DEFAULT,
                    Buttons.BUTTON_WIDTH_DEFAULT, Buttons.BUTTON_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - Buttons.X_OFFSET_CENTER, yPos, Buttons.BUTTON_WIDTH, Buttons.BUTTON_HEIGHT,
                null);
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        Gamestate.state = gameState;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
