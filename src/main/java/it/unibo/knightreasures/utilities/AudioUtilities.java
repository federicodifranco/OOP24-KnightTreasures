package it.unibo.knightreasures.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import it.unibo.knightreasures.utilities.ModelConstants.SongGame;

public class AudioUtilities {

    public Clip[] songs;
    private int currentSongId;
    private boolean isMuted;

    public AudioUtilities() {
        loadSongs();
    }

    public void stopSong() {
        if (songs[currentSongId] != null && songs[currentSongId].isActive()) {
            songs[currentSongId].stop();
        }
    }

    public void playMenuSong() {
        playSong(SongGame.MENU_SONG);
    }

    public void playLevelSong() {
        playSong(SongGame.LEVEL_SONG);
    }

    public void playSong(int song) {
        stopSong();
        currentSongId = song;
        updateVolume();
        if (songs[currentSongId] != null) {
            songs[currentSongId].setMicrosecondPosition(0);
            songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void setMuted() {
        this.isMuted = !isMuted;
        for (Clip c : songs) {
            if (c != null) {
                BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(isMuted);
            }
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setVolume(float volume) {
        volume = Math.max(SongGame.VOLUME_OFF, Math.min(SongGame.VOLUME_BASE, volume));
        for (Clip c : songs) {
            if (c != null) {
                FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
                float min = gainControl.getMinimum();
                float max = gainControl.getMaximum();
                gainControl.setValue(min + (max - min) * volume);
            }
        }
    }

    private void loadSongs() {
        final File directory = new File("src/main/resources/songs");
        File[] audioFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));
        if (audioFiles == null || audioFiles.length == 0) {
            songs = new Clip[0];
            return;
        }
        Arrays.sort(audioFiles, Comparator.comparing(File::getName));
        songs = new Clip[audioFiles.length];
        int index = 0;
        for (File file : audioFiles) {
            songs[index] = getClip(file);
            index++;
        }
    }

    private Clip getClip(File file) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateVolume() {
        if (songs[currentSongId] != null) {
            FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(((gainControl.getMaximum() - gainControl.getMinimum()) * SongGame.VOLUME_BASE) + gainControl.getMinimum());
        }
    }
}