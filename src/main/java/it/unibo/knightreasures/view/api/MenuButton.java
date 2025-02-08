package it.unibo.knightreasures.view.api;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents a button in the game menu, handling rendering, interaction, 
 * and game state changes.
 */
public interface MenuButton {

    /**
     * Draws the button on the screen.
     *
     * @param g the {@link Graphics} object used for rendering.
     */
    void draw(Graphics g);

    /**
     * Updates the button state, including animations or interactions.
     */
    void update();

    /**
     * Checks if the mouse is hovering over the button.
     *
     * @return {@code true} if the mouse is over the button, {@code false} otherwise.
     */
    boolean isMouseOver();

    /**
     * Sets the mouse hover state for the button.
     *
     * @param mouseOver {@code true} if the mouse is over the button, {@code false} otherwise.
     */
    void setMouseOver(boolean mouseOver);

    /**
     * Checks if the button is currently being pressed.
     *
     * @return {@code true} if the button is pressed, {@code false} otherwise.
     */
    boolean isMousePressed();

    /**
     * Sets the button press state.
     *
     * @param mousePressed {@code true} if the button is pressed, {@code false} otherwise.
     */
    void setMousePressed(boolean mousePressed);

    /**
     * Gets the bounding box of the button for collision detection.
     *
     * @return the {@link Rectangle} representing the button's bounds.
     */
    Rectangle getBounds();

    /**
     * Applies the corresponding game state when the button is clicked.
     */
    void applyGameState();

    /**
     * Resets the button interaction states, such as mouse over and pressed.
     */
    void resetBools();
}
