package it.unibo.knightreasures.view.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(5);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
