package it.unibo.knightreasures.view.impl;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.inputs.MouseInputs;
import it.unibo.knightreasures.view.inputs.KeyboardInputs;

public class ApplicationPanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;
    private BufferedImage[][] animation;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = PlayerValues.IDLE;
    private int playerDiretion = -1;
    private boolean moving = false;

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
        for (int j = 0; j < animation.length; j++) {
            for (int i = 0; i < animation[j].length; i++) {
                animation[j][i] = img.getSubimage(i * Player.PLAYER_WIDTH, j * Player.PLAYER_HEIGHT, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
            }
        }
    }

    public void setDirection(int direction) {
        this.playerDiretion = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= PlayerValues.INDEX_SPRITE) {
                aniIndex = 0;
            }
        }
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDiretion) {
                case Directions.LEFT:
                    xDelta -= 3;
                    break;
                case Directions.RIGHT:
                    xDelta += -3;
                    break;
                case Directions.UP:
                    yDelta -= 3;
                    break;
                case Directions.DOWN:
                    yDelta += -3;
                    break;
            }
        }
    }

    public void setAnimation() {
        if (moving) {
            playerAction = PlayerValues.RUN;
        } else {
            playerAction = PlayerValues.IDLE;
        }
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void resetPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void updateGame() {
        updateAnimation();
        setAnimation();
        updatePosition();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(animation[playerAction][aniIndex], (int)xDelta, (int)yDelta, Player.PLAYER_WIDTH,Player.PLAYER_HEIGHT, null);

    }

}
