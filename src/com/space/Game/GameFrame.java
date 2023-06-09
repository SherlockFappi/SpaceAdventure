package com.space.Game;

import com.space.Utility.DelayToFrameRate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("InfiniteLoopStatement")
public class GameFrame {
    private static final int framerate = 60;
    private static int currentFrame = 0;

    private int ammo = 50; // Munition

    private final JLabel ammoLabel;

    private static class Panel extends JPanel {
        Player player;
        BufferedImage playerImage;
        BufferedImage backgroundImage;
        BufferedImage exhaust_S;
        BufferedImage exhaust_L;

        AsteroidController asteroidController = new AsteroidController();
        private ArrayList<Asteroid> asteroidList = new ArrayList<>();
        private Asteroid asteroid;

        KeyHandler keyHandler;
        private ArrayList<Shot> shotList = new ArrayList<>();
        private Shot shot;


        public Panel(Player player, KeyHandler keyHandler) {
            this.player = player;
            this.keyHandler = keyHandler;

            try {
                this.playerImage = ImageIO.read(getClass().getResource("/rocket.png"));
                this.backgroundImage = ImageIO.read(getClass().getResource("/background.png"));
                this.exhaust_S = ImageIO.read(getClass().getResource("/rocket_antrieb_small.png"));
                this.exhaust_L = ImageIO.read(getClass().getResource("/rocket_antrieb_big.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int bgHeight;
            bgHeight = (getHeight() * currentFrame)/framerate;
            g.drawImage(backgroundImage, 0, bgHeight, this);
            g.drawImage(backgroundImage, 0, bgHeight-getHeight(), this);

            //System.out.println(player.getX() + " " + player.getY() + " " + getWidth() + " " + getHeight());
            g.drawImage(playerImage, player.getX(), player.getY(), this);
            if (KeyHandler.moveUp) g.drawImage(exhaust_L, player.getX()+25, player.getY()+151, this);
            else if (!KeyHandler.moveDown) g.drawImage(exhaust_S, player.getX()+25, player.getY()+151, this);

            // draw shots
            this.shotList = keyHandler.getShotList();
            for(int i = 0; i < shotList.size(); i++) {
                shot = shotList.get(i);
                g.drawImage(shot.getImage(), shot.getXpos(), shot.getYpos(), this);
                shot.setYpos(shot.getYpos() - shot.getSpeed());
                if (shot.getYpos() < -30) {
                    shotList.remove(shot);
                }
            }
            keyHandler.setShotList(this.shotList);

            // draw asteroids
            this.asteroidList = asteroidController.getAsteroidList();
            for (int i = 0; i < asteroidList.size(); i++) {
                asteroid = asteroidList.get(i);
                g.drawImage(asteroid.getImage(), asteroid.getX(), asteroid.getY(), this);
                asteroid.setY(asteroid.getY() + asteroid.getSpeed());
                if (asteroid.getY() > 720) {
                    asteroidList.remove(i);
                }
            }
            asteroidController.setAsteroidList(this.asteroidList);

            for (int i = 0; i < asteroidList.size(); i++) {
                asteroid = asteroidList.get(i);
                for (int j = 0; j < shotList.size(); j++) {
                    shot = shotList.get(j);
                    if ((asteroid.getX() < shot.getXpos() && asteroid.getX() + 50 > shot.getXpos())) {
                        if (asteroid.getY() - 30 < shot.getYpos() && asteroid.getY() + 50 >= shot.getYpos()) {
                            asteroidList.remove(i);
                            shotList.remove(j);
                        }
                    }
                }

                if (asteroid.getX() > player.getX() - 90 && asteroid.getX() + 40 < player.getX()) {
                    if (asteroid.getY() - 150 < player.getY() && asteroid.getY() + 50 >= player.getY()) {
                        asteroidList.remove(i);
                        System.out.println("Player hit");
                    }
                }
            }
            asteroidController.setAsteroidList(this.asteroidList);
            keyHandler.setShotList(this.shotList);

            //g.dispose();
        }
    }

    private static JFrame frame;

    public GameFrame() {
        Player player = new Player(590, 450);
        KeyHandler keyHandler = new KeyHandler(this,player);
        DelayToFrameRate frameController = new DelayToFrameRate(60, true);

        ammoLabel = new JLabel();
        ammoLabel.setText(String.valueOf(ammo));
        ammoLabel.setFont(new Font("Arial", Font.BOLD, 50));
        ammoLabel.setVisible(true);

        Panel panel = new Panel(player, keyHandler);

        panel.add(ammoLabel);

        frame = new JFrame();

        frame.add(panel);
        frame.addKeyListener(keyHandler);

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