package it.unibo.knightreasures.utilities;

import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.view.impl.MenuButton;

public class State {
    protected ApplicationImpl game;

    public State(ApplicationImpl game) {
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public ApplicationImpl getGame() {
        return game;
    }
}
