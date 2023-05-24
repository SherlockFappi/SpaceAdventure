package com.space.Utility;

import com.space.Game.MenuFrame;

public class Main {
    private static MenuFrame menuFrame;

    private Main() {
        menuFrame = new MenuFrame();
    }

    public static void main(String[] args) {
        new Main();
    }
}