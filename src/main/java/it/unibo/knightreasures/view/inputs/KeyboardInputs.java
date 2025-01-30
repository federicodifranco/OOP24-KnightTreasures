package it.unibo.knightreasures.view.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.knightreasures.utilities.ViewConstants.Directions;
import it.unibo.knightreasures.view.impl.ApplicationPanel;

public class KeyboardInputs implements KeyListener {

    private ApplicationPanel gamePanel;

    public KeyboardInputs(ApplicationPanel gamPanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.setDirection(Directions.UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.setDirection(Directions.LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.setDirection(Directions.DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.setDirection(Directions.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
        }
    }

}
