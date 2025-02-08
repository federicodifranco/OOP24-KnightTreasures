package it.unibo.knightreasures.view.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.view.impl.ApplicationPanel;

/**
 * Handles mouse inputs for the game, including clicks and movement.
 */
public final class MouseInputs implements MouseListener, MouseMotionListener {

    /**
     * Reference to the game panel.
     */
    private final ApplicationPanel gamePanel;

    /**
     * Constructs a new MouseInputs instance.
     *
     * @param gamePanel the application panel handling mouse interactions.
     */
    public MouseInputs(final ApplicationPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Handles mouse click events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU ->
                this.gamePanel.getGame().getMenu().mouseClicked(e);
            case SETTINGS ->
                this.gamePanel.getGame().getSettings().mouseClicked(e);
            case PLAYING ->
                this.gamePanel.getGame().getPlaying().mouseClicked(e);
            default -> {
            }
        }
    }

    /**
     * Handles mouse press events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU ->
                this.gamePanel.getGame().getMenu().mousePressed(e);
            case SETTINGS ->
                this.gamePanel.getGame().getSettings().mousePressed(e);
            case PLAYING ->
                this.gamePanel.getGame().getPlaying().mousePressed(e);
            default -> {
            }
        }
    }

    /**
     * Handles mouse release events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU ->
                this.gamePanel.getGame().getMenu().mouseReleased(e);
            case SETTINGS ->
                this.gamePanel.getGame().getSettings().mouseReleased(e);
            case PLAYING ->
                this.gamePanel.getGame().getPlaying().mouseReleased(e);
            default -> {
            }
        }
    }

    /**
     * Handles mouse enter events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseEntered(final MouseEvent e) {
        // No specific action needed on mouse enter.
    }

    /**
     * Handles mouse exit events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseExited(final MouseEvent e) {
        // No specific action needed on mouse exit.
    }

    /**
     * Handles mouse drag events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseDragged(final MouseEvent e) {
        // No specific action needed for dragging.
    }

    /**
     * Handles mouse movement events.
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU ->
                this.gamePanel.getGame().getMenu().mouseMoved(e);
            case SETTINGS ->
                this.gamePanel.getGame().getSettings().mouseMoved(e);
            case PLAYING ->
                this.gamePanel.getGame().getPlaying().mouseMoved(e);
            default -> {
            }
        }
    }
}
