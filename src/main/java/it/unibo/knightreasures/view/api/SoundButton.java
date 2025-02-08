package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

/**
 * Represents a sound button that controls audio settings in the game.
 * This button can toggle between muted and unmuted states and handle mouse interactions.
 */
public interface SoundButton {

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
     * Checks if the sound is muted.
     *
     * @return {@code true} if the sound is muted, {@code false} otherwise.
     */
    boolean isMuted();

    /**
     * Sets the muted state of the sound.
     *
     * @param muted {@code true} to mute the sound, {@code false} to unmute.
     */
    void setMuted(boolean muted);

    /**
     * Updates the button state, handling animations or interactions.
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
