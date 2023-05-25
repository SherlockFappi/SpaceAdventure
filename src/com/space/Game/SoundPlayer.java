package com.space.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private final String url;

    public SoundPlayer (String url) {
        this.url = url;
    }

    public void playSound () {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inStream = AudioSystem.getAudioInputStream(getClass().getResource("/shoot.wav"));
                clip.open(inStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = -25.0f; // Hier kannst du den gewünschten Lautstärkewert einstellen, z.B. -10.0f für 10 dB Lautstärkereduktion
                gainControl.setValue(volume);
                clip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
