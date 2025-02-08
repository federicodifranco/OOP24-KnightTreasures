package it.unibo.knightreasures.view.impl;

import java.awt.Rectangle;

import it.unibo.knightreasures.view.api.PauseButton;

/**
 * Represents a button used in the pause menu.
 */
public class PauseButtonImpl implements PauseButton {

    private int x, y, width, height, rowIndex;
    private Rectangle bounds;

    /**
     * Constructs a PauseButton instance.
     *
     * @param x the X coordinate of the button.
     * @param y the Y coordinate of the button.
     * @param width the width of the button.
     * @param height the height of the button.
     * @param rowIndex the row index for button sprite selection.
     */
    public PauseButtonImpl(final int x, final int y, final int width, final int height, final int rowIndex) {
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
    @Override
    public int getX() {
        return x;
    }

    /**
     * Sets the X coordinate of the button.
     *
     * @param x the new X coordinate.
     */
    @Override
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate of the button.
     *
     * @return the Y coordinate.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate of the button.
     *
     * @param y the new Y coordinate.
     */
    @Override
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Gets the width of the button.
     *
     * @return the width.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the button.
     *
     * @param width the new width.
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Gets the height of the button.
     *
     * @return the height.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the button.
     *
     * @param height the new height.
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Gets the row index of the button sprite.
     *
     * @return the row index.
     */
    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets the row index of the button sprite.
     *
     * @param rowIndex the new row index.
     */
    @Override
    public void setRowIndex(final int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Gets the bounding box of the button.
     *
     * @return the bounding box.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(bounds);
    }

    /**
     * Sets the bounding box of the button.
     *
     * @param bounds the new bounding box.
     */
    @Override
    public void setBounds(final Rectangle bounds) {
        this.bounds = new Rectangle(bounds);
    }
}
