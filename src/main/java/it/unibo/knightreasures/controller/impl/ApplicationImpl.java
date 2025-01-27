package it.unibo.knightreasures.controller.impl;

public class ApplicationImpl implements Runnable {

    private ApplicationPanel applicationPanel;
    private ApplicationWindow applicationWindow;
    private Thread gameThread;

    public ApplicationImpl() {
        applicationPanel = new ApplicationPanel();
        applicationWindow = new ApplicationWindow(applicationPanel);
        applicationPanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / Window.FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        int fps = 0;

        while (true) {
            now = System.nanoTime();
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                fps++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + fps);
                fps = 0;
            }
        }
    }
}
