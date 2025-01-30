package it.unibo.knightreasures.view.impl;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.inputs.MouseInputs;
import it.unibo.knightreasures.view.inputs.KeyboardInputs;

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
