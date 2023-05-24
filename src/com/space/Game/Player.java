package com.space.Game;

public class Player {
    private static int x, y;
    private static final int width = 1264, height = 681, playerWidth = 100, playerHeight = 160;

    public Player (int x, int y) {
        Player.x = x;
        Player.y = y;
    }

    public void setX (int x) {
        Player.x = Math.max(0, Math.min(width-playerWidth, x));
    }

    public void setY (int y) {
        Player.y = Math.max(0, Math.min(height-(playerHeight+50), y));
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }
}