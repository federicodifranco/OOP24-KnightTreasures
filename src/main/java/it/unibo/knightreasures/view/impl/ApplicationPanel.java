package it.unibo.knightreasures.view.impl;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.inputs.KeyboardInputs;
import it.unibo.knightreasures.view.inputs.MouseInputs;

/**
 * Represents the main game panel where rendering occurs. It handles user inputs
 * (keyboard and mouse) and manages the game display.
 */
public final class ApplicationPanel extends JPanel {

    /**
     * Serial version UID for serialization compatibility.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The main game application instance.
     */
    private final transient ApplicationImpl game;

    /**
     * Constructs the application panel and initializes input handlers.
     *
     * @param game the game application instance.
     */
    public ApplicationPanel(final ApplicationImpl game) {
        this.game = game;
        final MouseInputs mouseInputs = new MouseInputs(this);

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Sets the panel size based on game window dimensions.
     */
    private void setPanelSize() {
        final Dimension size = new Dimension(Window.GAME_WIDTH, Window.GAME_HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Updates the game state.
     */
    public void updateGame() {
        // Game update logic (if any) should be added here.
    }

    /**
     * Paints the game components on the panel.
     *
     * @param g the graphics object used for rendering.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * Gets the game instance associated with this panel.
     *
     * @return the game application instance.
     */
    public ApplicationImpl getGame() {
        return this.game;
    }
}
