package it.unibo.knightreasures.view.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.view.impl.ApplicationPanel;

public class KeyboardInputs implements KeyListener {

    private ApplicationPanel gamePanel;

    public KeyboardInputs(ApplicationPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:

                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }

       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:

                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
        
    }

}
