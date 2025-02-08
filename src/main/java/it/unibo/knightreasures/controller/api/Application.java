package it.unibo.knightreasures.controller.api;

import java.awt.Graphics;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.AudioUtilities;
import it.unibo.knightreasures.view.impl.Audio;
import it.unibo.knightreasures.view.impl.Menu;
import it.unibo.knightreasures.view.impl.Settings;

/**
 * Defines the main application controller interface.
 * It manages the game states, rendering, and audio functionalities.
 */
public interface Application {

    /**
     * Updates the current state of the application.
     */
    void update();

    /**
     * Renders the current game state on the screen.
     *
     * @param g The graphics object used for rendering.
     */
    void render(Graphics g);

    /**
     * Handles actions when the game window loses focus.
     */
    void windowLostFocus();

    /**
     * Retrieves the menu instance.
     *
     * @return The menu instance.
     */
    Menu getMenu();

    /**
     * Retrieves the gameplay instance.
     *
     * @return The gameplay instance.
     */
    GameplayImpl getPlaying();

    /**
     * Retrieves the settings instance.
     *
     * @return The settings instance.
     */
    Settings getSettings();

    /**
     * Retrieves the audio manager instance.
     *
     * @return The audio manager instance.
     */
    Audio getAudio();

    /**
     * Retrieves the audio utilities instance.
     *
     * @return The audio utilities instance.
     */
    AudioUtilities getAudioUtilities();
}
