package it.unibo.knightreasures.controller.api;

import java.awt.Graphics;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.AudioUtilities;
import it.unibo.knightreasures.view.impl.Audio;
import it.unibo.knightreasures.view.impl.Menu;
import it.unibo.knightreasures.view.impl.Settings;

public interface Application {
    
    void update();

    void render(Graphics g);

    void windowLostFocus();

    Menu getMenu();

    GameplayImpl getPlaying();

    Settings getSettings();

    Audio getAudio();

    AudioUtilities getAudioUtilities();
}
