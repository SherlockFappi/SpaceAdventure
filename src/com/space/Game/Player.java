package com.space.Game;

public class Player {
    private static int x, y;

    public Player (int x, int y) {
        Player.x = x;
        Player.y = y;
    }

    public void setX (int x) {
        Player.x = x;
    }

    public void setY (int y) {
        Player.y = y;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }
}