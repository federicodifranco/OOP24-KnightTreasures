package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

/**
 * Represents the buttons for Resume, Restart, and Home functionalities in the game.
 * These buttons handle interactions such as mouse hovering and pressing.
 */
public interface ResumeRestartHomeButtons {

    /**
     * Checks if the mouse is currently hovering over the button.
     *
     * @return {@code true} if the mouse is over the button, {@code false} otherwise.
     */
    boolean isMouseOver();

    /**
     * Sets whether the mouse is hovering over the button.
     *
     * @param mouseOver {@code true} if the mouse is over the button, {@code false} otherwise.
     */
    void setMouseOver(boolean mouseOver);

    /**
     * Checks if the mouse button is currently pressed on this button.
     *
     * @return {@code true} if the button is pressed, {@code false} otherwise.
     */
    boolean isMousePressed();

    /**
     * Sets whether the button is pressed.
     *
     * @param mousePressed {@code true} if the button is pressed, {@code false} otherwise.
     */
    void setMousePressed(boolean mousePressed);

    /**
     * Updates the state of the button, handling animations or interactions.
     */
    void update();

    /**
     * Draws the button on the screen.
     *
     * @param g the {@link Graphics} object used for rendering.
     */
    void draw(Graphics g);

    /**
     * Resets the button states, clearing hover and pressed effects.
     */
    void resetBools();
}
