package it.unibo.knightreasures.view.api;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Represents the view interface for the game, defining methods
 * for updating, rendering, and handling user input events.
 */
public interface View {

    /**
     * Updates the view state.
     */
    void update();

    /**
     * Draws the game view.
     *
     * @param g the graphics context used for rendering.
     */
    void draw(Graphics g);

    /**
     * Handles mouse click events.
     *
     * @param e the mouse event.
     */
    void mouseClicked(MouseEvent e);

    /**
     * Handles mouse press events.
     *
     * @param e the mouse event.
     */
    void mousePressed(MouseEvent e);

    /**
     * Handles mouse release events.
     *
     * @param e the mouse event.
     */
    void mouseReleased(MouseEvent e);

    /**
     * Handles mouse movement events.
     *
     * @param e the mouse event.
     */
    void mouseMoved(MouseEvent e);

    /**
     * Handles key press events.
     *
     * @param e the key event.
     */
    void keyPressed(KeyEvent e);

    /**
     * Handles key release events.
     *
     * @param e the key event.
     */
    void keyReleased(KeyEvent e);
}
