package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ModelConstants.SongGame;
import it.unibo.knightreasures.utilities.ViewConstants.AudioButtons;
import it.unibo.knightreasures.view.api.View;

/**
 * Handles the game's audio settings, including volume control buttons. This
 * class manages sound settings using volume on/off buttons.
 */
public final class Audio implements View {

    /**
     * Array containing the sound buttons for volume control.
     */
    private final SoundButtonImpl[] btns = new SoundButtonImpl[ButtonsValues.AUDIO_NUM_BUTTONS];

    /**
     * Reference to the main game application.
     */
    private final ApplicationImpl game;

    /**
     * Constructs an Audio settings manager.
     *
     * @param game the game application instance.
     */
    public Audio(final ApplicationImpl game) {
        this.game = game;
        createSoundButtons();
    }

    /**
     * Initializes the sound buttons for controlling volume.
     */
    private void createSoundButtons() {
        btns[ButtonsValues.VOLUME_OFF] = new SoundButtonImpl(
                AudioButtons.VOLUME_OFF_X, AudioButtons.VOLUME_Y,
                AudioButtons.SOUND_SIZE, AudioButtons.SOUND_SIZE,
                ButtonsValues.FIRST_ROW_INDEX
        );
        btns[ButtonsValues.VOLUME_ON] = new SoundButtonImpl(
                AudioButtons.VOLUME_ON_X, AudioButtons.VOLUME_Y,
                AudioButtons.SOUND_SIZE, AudioButtons.SOUND_SIZE,
                ButtonsValues.SECOND_ROW_INDEX
        );
    }

    /**
     * Updates the state of the audio buttons.
     */
    @Override
    public void update() {
        for (final SoundButtonImpl sb : btns) {
            sb.update();
        }
    }

    /**
     * Draws the audio buttons.
     *
     * @param g the graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        for (final SoundButtonImpl sb : btns) {
            sb.draw(g);
        }
    }

    /**
     * Handles mouse press events on volume buttons.
     *
     * @param e the MouseEvent.
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        if (isIn(e, btns[ButtonsValues.VOLUME_OFF])) {
            btns[ButtonsValues.VOLUME_OFF].setMousePressed(true);
        } else if (isIn(e, btns[ButtonsValues.VOLUME_ON])) {
            btns[ButtonsValues.VOLUME_ON].setMousePressed(true);
        }
    }

    /**
     * Handles mouse release events, toggling the audio state.
     *
     * @param e the MouseEvent.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isIn(e, btns[ButtonsValues.VOLUME_ON])
                && btns[ButtonsValues.VOLUME_ON].isMousePressed()
                && !game.getAudioUtilities().isMuted()) {
            game.getAudioUtilities().setMuted();
        } else if (isIn(e, btns[ButtonsValues.VOLUME_OFF])
                && btns[ButtonsValues.VOLUME_OFF].isMousePressed()
                && game.getAudioUtilities().isMuted()) {
            game.getAudioUtilities().setMuted();
            game.getAudioUtilities().setVolume(SongGame.VOLUME_BASE);
        }

        btns[ButtonsValues.VOLUME_OFF].resetBools();
        btns[ButtonsValues.VOLUME_ON].resetBools();
    }

    /**
     * Handles mouse movement over the buttons.
     *
     * @param e the MouseEvent.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        btns[ButtonsValues.VOLUME_OFF].setMouseOver(false);
        btns[ButtonsValues.VOLUME_ON].setMouseOver(false);

        if (isIn(e, btns[ButtonsValues.VOLUME_OFF])) {
            btns[ButtonsValues.VOLUME_OFF].setMouseOver(true);
        } else if (isIn(e, btns[ButtonsValues.VOLUME_ON])) {
            btns[ButtonsValues.VOLUME_ON].setMouseOver(true);
        }
    }

    /**
     * Checks if the mouse event occurred within a button's bounds.
     *
     * @param e the MouseEvent.
     * @param b the SoundButton to check.
     * @return true if the mouse is inside the button's bounds, false otherwise.
     */
    private boolean isIn(final MouseEvent e, final SoundButtonImpl b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Handles mouse click events.
     *
     * @param e the MouseEvent.
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        // No action needed
    }

    /**
     * Handles key press events.
     *
     * @param e the KeyEvent.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        // No action needed
    }

    /**
     * Handles key release events.
     *
     * @param e the KeyEvent.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        // No action needed
    }
}
