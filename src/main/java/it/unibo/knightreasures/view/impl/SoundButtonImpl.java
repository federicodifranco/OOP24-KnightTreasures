package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.AudioButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.view.api.SoundButton;

/**
 * Represents a sound button used for audio settings in the game.
 */
public final class SoundButtonImpl extends PauseButtonImpl implements SoundButton {

    private BufferedImage[] soundImgs;
    private int index;
    private boolean mouseOver, mousePressed, muted;

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

   @Override
    public void draw(final Graphics g) {
        g.drawImage(soundImgs[index], getX(), getY(), getWidth(), getHeight(), null);
    }

   @Override
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

   @Override
    public boolean isMouseOver() {
        return mouseOver;
    }

   @Override
    public void setMouseOver(final boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

   @Override
    public boolean isMousePressed() {
        return mousePressed;
    }

   @Override
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

   @Override
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

   @Override
    public boolean isMuted() {
        return muted;
    }

   @Override
    public void setMuted(final boolean muted) {
        this.muted = muted;
    }
}
