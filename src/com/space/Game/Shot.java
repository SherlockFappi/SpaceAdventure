package com.space.Game;

import java.awt.image.BufferedImage;

public class Shot {
    private int xpos;
    private int ypos;

    private final int speed;

    private final BufferedImage image;

    public Shot (int xpos, int ypos, int speed, BufferedImage image) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.speed = speed;
        this.image = image;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getXpos () {
        return this.xpos;
    }

    public int getYpos () {
        return this.ypos;
    }

    public int getSpeed () {
        return this.speed;
    }

    public BufferedImage getImage () {
        return this.image;
    }
}
