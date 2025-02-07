package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.AudioButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Images;

/**
 * Represents a sound button used for audio settings in the game.
 */
public final class SoundButtonImpl extends PauseButtonImpl {

    /**
     * Array containing images for different button states.
     */
    private BufferedImage[] soundImgs;

    /**
     * Index indicating the current button state.
     */
    private int index;

    /**
     * Indicates whether the mouse is hovering over the button.
     */
    private boolean mouseOver;

    /**
     * Indicates whether the button is pressed.
     */
    private boolean mousePressed;

    /**
     * Indicates whether the sound is muted.
     */
    private boolean muted;

    /**
     * Constructs a SoundButton instance.
     *
     * @param x        the X coordinate of the button.
     * @param y        the Y coordinate of the button.
     * @param width    the width of the button.
     * @param height   the height of the button.
     * @param rowIndex the index for the button sprite row.
     */
    public SoundButtonImpl(final int x, final int y, final int width, final int height, final int rowIndex) {
        super(x, y, width, height, rowIndex);
        loadSoundImgs();
    }

    /**
     * Loads images for the sound button states.
     */
    private void loadSoundImgs() {
        final BufferedImage temp = ResourceFuncUtilities.loadSources(Images.VOLUME_BUTTONS);
        soundImgs = new BufferedImage[3];
        for (int i = 0; i < soundImgs.length; i++) {
            soundImgs[i] = temp.getSubimage(i * AudioButtons.SOUND_SIZE_DEFAULT,
                    getRowIndex() * AudioButtons.SOUND_SIZE_DEFAULT, AudioButtons.SOUND_SIZE_DEFAULT,
                    AudioButtons.SOUND_SIZE_DEFAULT);
        }
    }

    /**
     * Draws the sound button on the screen.
     *
     * @param g the graphics object used for rendering.
     */
    public void draw(final Graphics g) {
        g.drawImage(soundImgs[index], getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Updates the button state based on interactions.
     */
    public void update() {
        if (muted) {
            setRowIndex(ButtonsValues.SECOND_ROW_INDEX);
        } else {
            setRowIndex(ButtonsValues.FIRST_ROW_INDEX);
        }
        index = ButtonsValues.FIRST_ROW_INDEX;
        if (mouseOver) {
            index = ButtonsValues.SECOND_ROW_INDEX;
        }
        if (mousePressed) {
            index = ButtonsValues.THIRD_ROW_INDEX;
        }
    }

    /**
     * Checks if the mouse is hovering over the button.
     *
     * @return true if the mouse is over the button, false otherwise.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Sets whether the mouse is hovering over the button.
     *
     * @param mouseOver true if the mouse is over the button, false otherwise.
     */
    public void setMouseOver(final boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Checks if the button is pressed.
     *
     * @return true if the button is pressed, false otherwise.
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Sets whether the button is pressed.
     *
     * @param mousePressed true if the button is pressed, false otherwise.
     */
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Resets the button state flags.
     */
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Checks if the sound is muted.
     *
     * @return true if the sound is muted, false otherwise.
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * Sets the muted state of the button.
     *
     * @param muted true to mute the sound, false to unmute.
     */
    public void setMuted(final boolean muted) {
        this.muted = muted;
    }
}
