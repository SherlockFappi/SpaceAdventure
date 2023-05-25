package com.space.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Asteroid {
    private int xpos;
    private int ypos;
    private int speed;
    private BufferedImage image;
    private Random random;

    public Asteroid () {
        random = new Random();
        this.xpos = random.nextInt(1200);
        this.ypos = -50;

        //this.speed = random.nextInt(10) + 5;
        this.speed = 2;

        int asteroidIndex = random.nextInt(3);
        switch(asteroidIndex) {
            case 0 -> {
                try {
                    this.image = ImageIO.read(new File("src/com/space/Textures/asteroids/asteroid_01.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case 1 -> {
                try {
                    this.image = ImageIO.read(new File("src/com/space/Textures/asteroids/asteroid_02.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                try {
                    this.image = ImageIO.read(new File("src/com/space/Textures/asteroids/asteroid_03.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getX () {
        return this.xpos;
    }

    public int getY () {
        return this.ypos;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setY (int ypos) {
        this.ypos = ypos;
    }

    public BufferedImage getImage () {
        return this.image;
    }
}
