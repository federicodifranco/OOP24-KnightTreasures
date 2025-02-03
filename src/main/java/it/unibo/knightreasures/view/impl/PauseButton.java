package it.unibo.knightreasures.view.impl;

import java.awt.Rectangle;

public class PauseButton {

    protected int x, y, width, height, rowIndex;
    private Rectangle bounds;

    public PauseButton(int x, int y, int widht, int height, int rowIndex) {
        this.x = x;
        this.y = y;
        this.width = widht;
        this.height = height;
        this.rowIndex = rowIndex;
        createdBounds();
    }

    private void createdBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}