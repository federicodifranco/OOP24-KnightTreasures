package it.unibo.knightreasures.view.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.view.impl.ApplicationPanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private ApplicationPanel gamePanel;

    public MouseInputs(ApplicationPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING:
                this.gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:

                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:

                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:

                break;
            case PLAYING:
                this.gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
        
    }

}
