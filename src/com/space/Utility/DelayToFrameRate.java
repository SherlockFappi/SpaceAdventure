package com.space.Utility;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class DelayToFrameRate {
    private final TimeUnit tu;
    private int nanosPerFrame;
    private boolean warningMode;

    public DelayToFrameRate (int framerate, boolean warningMode) {
        this.tu = TimeUnit.NANOSECONDS;
        this.nanosPerFrame = (int)(1000000000L / (long)framerate);
        this.warningMode = warningMode;
    }

    public void setFrameRate(int framerate) {
        this.nanosPerFrame = (int)(1000000000L / (long)framerate);
    }

    public void setWarningMode(boolean warningMode) {
        this.warningMode = warningMode;
    }

    public void delay(long time) {
        try {
            long delayAmount = (long)this.nanosPerFrame - time;
            if (delayAmount < 0L) {
                if (this.warningMode) {
                    System.err.println("Can't keep up! reduce framerate");
                }

                delayAmount = 0L;
            }

            this.tu.sleep(delayAmount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}