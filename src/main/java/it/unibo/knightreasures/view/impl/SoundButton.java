package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.AudioButtons;
import it.unibo.knightreasures.utilities.ViewConstants.Images;

public class SoundButton extends PauseButton {

    private BufferedImage[] soundImgs;
    private int index;
    private boolean mouseOver, mousePressed, muted;

    public SoundButton(int x, int y, int widht, int height, int rowIndex) {
        super(x, y, widht, height, rowIndex);
        loadSoundImgs();
    }

    private void loadSoundImgs() {
        BufferedImage temp = ResourceFuncUtilities.loadSources(Images.VOLUME_BUTTONS);
        soundImgs = new BufferedImage[3];
        for (int i = 0; i < soundImgs.length; i++) {
            soundImgs[i] = temp.getSubimage(i * AudioButtons.SOUND_SIZE_DEFAULT, rowIndex * AudioButtons.SOUND_SIZE_DEFAULT, AudioButtons.SOUND_SIZE_DEFAULT, AudioButtons.SOUND_SIZE_DEFAULT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(soundImgs[index], x, y, width, height, null);
    }

    public void update() {
        if (muted) rowIndex = ButtonsValues.SECOND_ROW_INDEX; 
        else rowIndex = ButtonsValues.FIRST_ROW_INDEX;
        index = ButtonsValues.FIRST_ROW_INDEX;
        if (mouseOver) index = ButtonsValues.SECOND_ROW_INDEX;
        if (mousePressed) index = ButtonsValues.THIRD_ROW_INDEX;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

}
