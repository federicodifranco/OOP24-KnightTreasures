package it.unibo.knightreasures.view.impl;

import java.awt.Rectangle;

/**
 * Represents a button used in the pause menu.
 */
public class PauseButton {

    /**
     * The X coordinate of the button.
     */
    private int x;

    /**
     * The Y coordinate of the button.
     */
    private int y;

    /**
     * The width of the button.
     */
    private int width;

    /**
     * The height of the button.
     */
    private int height;

    /**
     * The row index for button sprite selection.
     */
    private int rowIndex;

    /**
     * The bounding box of the button.
     */
    private Rectangle bounds;

    /**
     * Constructs a PauseButton instance.
     *
     * @param x        the X coordinate of the button.
     * @param y        the Y coordinate of the button.
     * @param width    the width of the button.
     * @param height   the height of the button.
     * @param rowIndex the row index for button sprite selection.
     */
    public PauseButton(final int x, final int y, final int width, final int height, final int rowIndex) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rowIndex = rowIndex;
        createBounds();
    }

    /**
     * Creates the bounding box for the button.
     */
    private void createBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Gets the X coordinate of the button.
     *
     * @return the X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X coordinate of the button.
     *
     * @param x the new X coordinate.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate of the button.
     *
     * @return the Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate of the button.
     *
     * @param y the new Y coordinate.
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Gets the width of the button.
     *
     * @return the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the button.
     *
     * @param width the new width.
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Gets the height of the button.
     *
     * @return the height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the button.
     *
     * @param height the new height.
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Gets the row index of the button sprite.
     *
     * @return the row index.
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets the row index of the button sprite.
     *
     * @param rowIndex the new row index.
     */
    public void setRowIndex(final int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Gets the bounding box of the button.
     *
     * @return the bounding box.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the bounding box of the button.
     *
     * @param bounds the new bounding box.
     */
    public void setBounds(final Rectangle bounds) {
        this.bounds = bounds;
    }
}
