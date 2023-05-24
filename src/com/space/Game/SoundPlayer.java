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
                clip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
