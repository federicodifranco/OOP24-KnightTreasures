package it.unibo.knightreasures.heart;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;

/**
 * Main entry point for the Knight Treasures game.
 */
public final class KnightTreasures {

    /**
     * Private constructor to prevent instantiation.
     */
    private KnightTreasures() { }

    /**
     * Main method to start the game.
     *
     * @param args the command-line arguments.
     */
    public static void main(final String[] args) {
        new ApplicationImpl();
    }
}
