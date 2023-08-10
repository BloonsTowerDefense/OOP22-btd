package btd.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for managing and playing game sounds.
 * The SoundManager class uses lazy initialization and provides a singleton instance for sound management.
 */
public final class SoundManager {

    public enum SoundType {

        BUTTON,
        BLOON_DEATH,
        SHOOT,
        BACKGROUND_MUSIC
        // Add more sound types here...
    }

    private final Map<SoundType, Clip> sounds = new HashMap<>();
    private boolean audioEnabled = true;

    /**
     * Private inner class responsible for lazy initialization of the SoundManager singleton instance.
     */
    private static class LazySoundManager {
        private static final SoundManager SINGLETON = new SoundManager();
    }

    /**
     * Returns the singleton instance of the SoundManager class.
     *
     * @return The singleton instance of the SoundManager class.
     */
    public static SoundManager getInstance() {
        return LazySoundManager.SINGLETON;
    }

    /**
     * Private constructor for initializing sound resources.
     */
    private SoundManager() {
        try {
            loadSound(SoundType.BUTTON, "/sounds/button.wav");
            loadSound(SoundType.BLOON_DEATH, "/sounds/bloon_death.wav");
            loadSound(SoundType.SHOOT, "/sounds/shoot.wav");
            loadSound(SoundType.BACKGROUND_MUSIC, "/sounds/background_music.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a sound clip from the specified resource path.
     *
     * @param soundType The SoundType associated with the sound.
     * @param resourcePath The path to the sound resource.
     * @throws Exception If an error occurs while loading the sound.
     */
    private void loadSound(SoundType soundType, String resourcePath) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        sounds.put(soundType, clip);
    }

    /**
     * Plays the specified sound.
     *
     * @param soundType The SoundType of the sound to be played.
     * @param loop Whether the sound should loop or not.
     */
    public void playSound(SoundType soundType, boolean loop) {
        if (audioEnabled) {
            Clip clip = sounds.get(soundType);
            if (clip != null) {
                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.setFramePosition(0);
                    clip.start();
                }
            }
        }
    }

    /**
     * Stops playing the specified sound.
     *
     * @param soundType The SoundType of the sound to be stopped.
     */
    public void stopSound(SoundType soundType) {
        Clip clip = sounds.get(soundType);
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * Stops playing all sounds.
     */
    public void stopAll() {
        sounds.values().forEach(Clip::stop);
    }

    /**
     * Plays the specified sound in a loop.
     *
     * @param soundType The SoundType of the sound to be played.
     */
    public void playInLoop(SoundType soundType) {
        if (audioEnabled) {
            Clip clip = sounds.get(soundType);
            if (clip != null) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    /**
     * Enables or disables audio.
     *
     * @param enabled Whether audio should be enabled or disabled.
     */
    public void setAudioEnabled(boolean enabled) {
        audioEnabled = enabled;
        if (!enabled) {
            stopAll();
        }
    }

    /**
     * Returns whether audio is enabled.
     *
     * @return {@code true} if audio is enabled, {@code false} otherwise.
     */
    public boolean isAudioEnabled() {
        return audioEnabled;
    }
}

