package it.unibo.knightreasures.view.impl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import it.unibo.knightreasures.utilities.ViewConstants.Window;

public class ApplicationWindow {

    private JFrame frame;

    public ApplicationWindow(ApplicationPanel applicationPanel) {

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
            public void windowGainedFocus(WindowEvent e) {
                applicationPanel.getGame().windowLostFocus();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }

        });
    }

}
