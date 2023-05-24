package com.space.Game;

import com.space.Utility.DelayToFrameRate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("InfiniteLoopStatement")
public class GameFrame {
    private static final int framerate = 60;
    private static int currentFrame = 0;

    private int ammo = 50; // Munition

    private Timer backgroundTimer;

    private JLabel ammoLabel;

    private static class Panel extends JPanel {
        Player player;
        BufferedImage playerImage;
        BufferedImage backgroundImage;

        public Panel(Player player) {
            this.player = player;

            try {
                this.playerImage = ImageIO.read(new File("src/com/space/Textures/rocket/rocket.png"));
                this.backgroundImage = ImageIO.read(new File("src/com/space/Textures/background.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            int y;
            y = (getHeight() * currentFrame)/framerate;
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, y, this);
            g.drawImage(backgroundImage, 0, y-getHeight(), this);
            g.drawImage(playerImage, player.getX(), player.getY(), this);
            //g.dispose();
        }
    }

    private static JFrame frame;

    public GameFrame() {
        DelayToFrameRate frameController = new DelayToFrameRate(60, true);
        Player player = new Player(590, 500);

        ammoLabel = new JLabel();
        ammoLabel.setText(String.valueOf(ammo));
        ammoLabel.setFont(new Font("Arial", Font.BOLD, 50));
        ammoLabel.setVisible(true);

        Panel panel = new Panel(player);

        panel.add(ammoLabel);

        frame = new JFrame();

        frame.add(panel);
        frame.addKeyListener(new KeyHandler(this, player));

        frame.setTitle("Space Adventure");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.setBackground(Color.BLACK);

        long time;
        while (true) {
            currentFrame = (currentFrame+1)%framerate;
            time = System.nanoTime();
            panel.repaint();
            time = System.nanoTime() - time;
            frameController.delay(time);
        }
    }

    public int getAmmo () {
        return this.ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
        ammoLabel.setText(String.valueOf(this.ammo));
    }
}