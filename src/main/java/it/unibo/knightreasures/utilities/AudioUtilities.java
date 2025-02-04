package it.unibo.knightreasures.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unibo.knightreasures.utilities.ModelConstants.SongGame;

/**
 * Utility class for handling audio playback in the game.
 */
public final class AudioUtilities {

    private static final Logger LOGGER = Logger.getLogger(AudioUtilities.class.getName());

    /**
     * Array of audio clips used in the game.
     */
    private Clip[] songs;

    /**
     * ID of the currently playing song.
     */
    private int currentSongId;

    /**
     * Indicates whether the audio is muted.
     */
    private boolean isMuted;

    /**
     * Constructs an instance of AudioUtilities and loads audio files.
     */
    public AudioUtilities() {
        loadSongs();
    }

    /**
     * Stops the currently playing song.
     */
    public void stopSong() {
        if (songs[currentSongId] != null && songs[currentSongId].isActive()) {
            songs[currentSongId].stop();
        }
    }

    /**
     * Plays the menu background music.
     */
    public void playMenuSong() {
        playSong(SongGame.MENU_SONG);
    }

    /**
     * Plays the level background music.
     */
    public void playLevelSong() {
        playSong(SongGame.LEVEL_SONG);
    }

    /**
     * Plays a specific song.
     *
     * @param song the ID of the song to be played.
     */
    public void playSong(final int song) {
        stopSong();
        currentSongId = song;
        updateVolume();
        if (songs[currentSongId] != null) {
            songs[currentSongId].setMicrosecondPosition(0);
            songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Toggles the mute state of the audio.
     */
    public void setMuted() {
        this.isMuted = !isMuted;
        for (final Clip c : songs) {
            if (c != null) {
                final BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(isMuted);
            }
        }
    }

    /**
     * Checks if the audio is muted.
     *
     * @return true if muted, false otherwise.
     */
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Sets the volume of the audio.
     *
     * @param volume the volume level to set.
     */
    public void setVolume(final float volume) {
        final float adjustedVolume = Math.max(SongGame.VOLUME_OFF, Math.min(SongGame.VOLUME_BASE, volume));
        for (final Clip c : songs) {
            if (c != null) {
                final FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
                final float min = gainControl.getMinimum();
                final float max = gainControl.getMaximum();
                gainControl.setValue(min + (max - min) * adjustedVolume);
            }
        }
    }

    /**
     * Loads all available audio files from the resources folder.
     */
    private void loadSongs() {
        final File directory = new File("src/main/resources/songs");
        final File[] audioFiles = directory.listFiles((dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(".wav"));
        if (audioFiles == null || audioFiles.length == 0) {
            songs = new Clip[0];
            return;
        }
        Arrays.sort(audioFiles, Comparator.comparing(File::getName));
        songs = new Clip[audioFiles.length];
        int index = 0;
        for (final File file : audioFiles) {
            songs[index] = getClip(file);
            index++;
        }
    }

    /**
     * Retrieves a Clip instance from an audio file.
     *
     * @param file the audio file to be loaded.
     * @return a Clip instance or null if an error occurs.
     */
    private Clip getClip(final File file) {
        try {
            final AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            final Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        } catch (UnsupportedAudioFileException e) {
            LOGGER.log(Level.SEVERE, "Unsupported audio file format: " + file.getName(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading audio file: " + file.getName(), e);
        } catch (LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Audio line unavailable for: " + file.getName(), e);
        }
        return null;
    }

    /**
     * Updates the volume of the currently playing song.
     */
    private void updateVolume() {
        if (songs[currentSongId] != null) {
            final FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(
                    ((gainControl.getMaximum() - gainControl.getMinimum()) * SongGame.VOLUME_BASE) + gainControl.getMinimum()
            );
        }
    }
}
