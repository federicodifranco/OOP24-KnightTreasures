package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.ModelConstants.ButtonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.AudioButtons;
import it.unibo.knightreasures.view.api.View;

public class Audio implements View {

    private final SoundButton[] btns = new SoundButton[ButtonsValues.AUDIO_NUM_BUTTONS];
    private final ApplicationImpl game;

    public Audio(ApplicationImpl game) {
        this.game = game;
        createdSoundButtons();
    }

    private void createdSoundButtons() {
        btns[ButtonsValues.VOLUME_OFF] = new SoundButton(AudioButtons.VOLUME_OFF_X, AudioButtons.VOLUME_Y, AudioButtons.SOUND_SIZE, AudioButtons.SOUND_SIZE, ButtonsValues.FIRST_ROW_INDEX);
        btns[ButtonsValues.VOLUME_ON] = new SoundButton(AudioButtons.VOLUME_ON_X, AudioButtons.VOLUME_Y, AudioButtons.SOUND_SIZE, AudioButtons.SOUND_SIZE, ButtonsValues.SECOND_ROW_INDEX);
    }

    @Override
    public void update() {
        for (SoundButton sb : btns) {
            sb.update();
        }
    }

   @Override
    public void draw(Graphics g) {
        for (SoundButton sb : btns) {
            sb.draw(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, btns[ButtonsValues.VOLUME_OFF])) {
            btns[ButtonsValues.VOLUME_OFF].setMousePressed(true);
        } else if (isIn(e, btns[ButtonsValues.VOLUME_ON])) {
            btns[ButtonsValues.VOLUME_ON].setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        

        btns[ButtonsValues.VOLUME_OFF].resetBools();
        btns[ButtonsValues.VOLUME_ON].resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        btns[ButtonsValues.VOLUME_OFF].setMouseOver(false);
        btns[ButtonsValues.VOLUME_ON].setMouseOver(false);

        if (isIn(e, btns[ButtonsValues.VOLUME_OFF])) {
            btns[ButtonsValues.VOLUME_OFF].setMouseOver(true);
        } else if (isIn(e, btns[ButtonsValues.VOLUME_ON])) {
            btns[ButtonsValues.VOLUME_ON].setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, SoundButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
