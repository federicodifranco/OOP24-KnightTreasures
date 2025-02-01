package it.unibo.knightreasures.view.impl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Represents the main application window for the game.
 * It initializes and manages the JFrame settings and listens for window focus events.
 */
public class ApplicationWindow {

    /** The main JFrame for the game application. */
    private final JFrame frame;

    /**
     * Constructs the application window and initializes its settings.
     *
     * @param applicationPanel the panel containing the game components.
     */
    public ApplicationWindow(final ApplicationPanel applicationPanel) {

        frame = new JFrame();
        frame.setSize(Window.GAME_WIDTH, Window.GAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(applicationPanel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(final WindowEvent e) {
                applicationPanel.getGame().windowLostFocus();
            }

            @Override
            public void windowLostFocus(final WindowEvent e) {
                // No action required when focus is lost.
            }
        });
    }
}
