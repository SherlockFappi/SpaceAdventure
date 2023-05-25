package com.space.Game;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AsteroidController {
    private ArrayList<Asteroid> asteroidList = new ArrayList<>();

    public AsteroidController () {
        Timer spawnTimer = new Timer();
        spawnTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                asteroidList.add(new Asteroid());
            }
        }, 3000, 1500);
    }

    public ArrayList<Asteroid> getAsteroidList () {
        return this.asteroidList;
    }

    public void setAsteroidList (ArrayList<Asteroid> asteroidList) {
        this.asteroidList = asteroidList;
    }
}
