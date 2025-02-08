package it.unibo.knightreasures.view.api;

import java.awt.Rectangle;

/**
 * Represents a button in the pause menu, handling positioning, sizing, and bounding area.
 */
public interface PauseButton {

    /**
     * Gets the x-coordinate of the button.
     *
     * @return the x-coordinate.
     */
    int getX();

    /**
     * Sets the x-coordinate of the button.
     *
     * @param x the new x-coordinate.
     */
    void setX(int x);

    /**
     * Gets the y-coordinate of the button.
     *
     * @return the y-coordinate.
     */
    int getY();

    /**
     * Sets the y-coordinate of the button.
     *
     * @param y the new y-coordinate.
     */
    void setY(int y);

    /**
     * Gets the width of the button.
     *
     * @return the button's width.
     */
    int getWidth();

    /**
     * Sets the width of the button.
     *
     * @param width the new width.
     */
    void setWidth(int width);

    /**
     * Gets the height of the button.
     *
     * @return the button's height.
     */
    int getHeight();

    /**
     * Sets the height of the button.
     *
     * @param height the new height.
     */
    void setHeight(int height);

    /**
     * Gets the bounding rectangle of the button.
     *
     * @return the {@link Rectangle} representing the button's bounds.
     */
    Rectangle getBounds();

    /**
     * Sets the bounding rectangle of the button.
     *
     * @param bounds the new bounding {@link Rectangle}.
     */
    void setBounds(Rectangle bounds);

    /**
     * Sets the row index of the button sprite.
     *
     * @param rowIndex the new row index.
     */
    void setRowIndex(int rowIndex);

    /**
     * Gets the row index of the button sprite.
     *
     * @return the row index.
     */
    int getRowIndex();
}
