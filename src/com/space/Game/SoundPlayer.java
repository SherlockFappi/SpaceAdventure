package com.space.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public SoundPlayer () {

    }

    public void playSound (String url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inStream = AudioSystem.getAudioInputStream(new File(new File("src\\com\\space\\Game\\Sounds\\" + url).getAbsolutePath()));
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
