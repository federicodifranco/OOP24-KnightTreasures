package it.unibo.knightreasures.view.impl;

import javax.swing.JPanel;
import java.awt.Graphics;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;

import java.awt.image.BufferedImage;

public class ApplicationPanel extends JPanel {

    private MouseInputs MouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img,subImg;
    private BufferedImage[][] animation;
    private int aniTick, aniIndex, aniSpeed = 15;

    public ApplicationPanel() {
        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimation();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        img = ResourceFuncUtilities.loadSources("knight");
    }

    private void loadAnimation(){
        animation = new BufferedImage[4][6];
        for(int j = 0; j < animation.length; j++) {
            for(int i = 0; i < animation[j].length; i++) {
                animation[j][i] = img.getSubimage(i * 83, j * 125, 83, 125);
            }
        }
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= idleAni.legth) {
                aniIndex = 0;
            }
        }
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void resetPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimation();
        g.drawImage(animation[2][2], (int)xDelta, (int)yDelta, 83,125, null);

    }

}
