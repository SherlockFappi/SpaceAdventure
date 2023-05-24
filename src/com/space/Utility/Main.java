package com.space.Utility;

import com.space.Game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static class MenuPanel extends JPanel {
        public void paintComponent(Graphics g) {

        }
    }

    private JFrame menuFrame;
    private JButton startButton;
    private MenuPanel menuPanel;

    private Main() {
        menuFrame = new JFrame();

        startButton = new JButton();
        startButton.setText("Start Game");
        startButton.addActionListener(e -> {
            new GameFrame();
            menuFrame.dispose();
        });
        startButton.setVisible(true);

        menuPanel = new MenuPanel();

        menuPanel.add(startButton);

        menuFrame = new JFrame();

        menuFrame.add(menuPanel);

        menuFrame.setTitle("Space Adventure");
        menuFrame.setSize(480, 360);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
        //new Main();
    }
}