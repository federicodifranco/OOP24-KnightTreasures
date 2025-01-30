package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;

import javax.swing.JPanel;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.view.inputs.KeyboardInputs;
import it.unibo.knightreasures.view.inputs.MouseInputs;

public class ApplicationPanel extends JPanel {

    private MouseInputs mouseInputs;
    private ApplicationImpl game;

    public ApplicationPanel(ApplicationImpl game) {
        this.game = game;

        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void updateGame() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public ApplicationImpl getGame() {
        return this.game;
    }

}
