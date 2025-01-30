package it.unibo.knightreasures.view.impl;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.view.inputs.KeyboardInputs;
import it.unibo.knightreasures.view.inputs.MouseInputs;

public class ApplicationPanel extends JPanel {

    private MouseInputs mouseInputs;
    private ApplicationImpl game;

    public ApplicationPanel(ApplicationImpl game) {
        this.game = game;

        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(Window.GAME_WIDTH, Window.GAME_HEIGHT);
        setPreferredSize(size);
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
