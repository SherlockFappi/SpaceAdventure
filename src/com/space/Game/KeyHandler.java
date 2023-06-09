package com.space.Game;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class KeyHandler implements KeyListener {
    private ArrayList<Shot> shotList = new ArrayList<>();

    private final GameFrame gameFrame;

    public static boolean moveUp = false;
    public static boolean moveDown = false;
    public static boolean moveLeft = false;
    public static boolean moveRight = false;

    private final int moveSpeedLeft = 7;
    private final int moveSpeedUp = 5;
    private final int moveSpeedDown = 3;

    private boolean canShoot = true;
    private final int shotSpeed = 200; // Schuss alle shotSpeed ms möglich
    Timer shootTimer;

    SoundPlayer shootSoundPlayer;
    Player player;

    public KeyHandler (GameFrame gameFrame, Player player) {
        this.gameFrame = gameFrame;
        this.player = player;

        shootSoundPlayer = new SoundPlayer("shoot.wav");

        shootTimer = new Timer();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (moveUp && moveDown) {
                    moveUp = false;
                    moveDown = false;
                }

                if (moveUp) player.setY(player.getY() - moveSpeedUp);
                if (moveDown) player.setY(player.getY() + moveSpeedDown);
                if (moveLeft) player.setX(player.getX() - moveSpeedLeft);
                if (moveRight) player.setX(player.getX() + moveSpeedLeft);
            }
        }, 0, 10);
    }

    public ArrayList<Shot> getShotList () {
        return this.shotList;
    }

    public void setShotList (ArrayList<Shot> shotList) {
        this.shotList = shotList;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && canShoot) {
            gameFrame.setAmmo(gameFrame.getAmmo() - 1);
            canShoot = false;
            shootSoundPlayer.playSound();
            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource("/shot_green.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            shotList.add(new Shot(player.getX() + 43, player.getY(), 10, image));
            shootTimer.scheduleAtFixedRate(new TimerTask() {
                int i = 0;
                @Override
                public void run() {
                    if (i < shotSpeed) i+=10;
                    else {
                        if (gameFrame.getAmmo() > 0) canShoot = true;
                        this.cancel();
                    }
                }
            }, 0, 10);
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN  || e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            moveRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            moveRight = false;
        }
    }
}