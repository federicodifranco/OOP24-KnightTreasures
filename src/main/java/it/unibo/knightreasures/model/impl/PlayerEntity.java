package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ViewConstants.Player;

public class PlayerEntity extends EntityManager{

    private BufferedImage[][] animation;
    private int aniTick, aniIndex;
    private int playerAction = PlayerValues.IDLE;
    private boolean moving = false, attacking = false;

    public PlayerEntity(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animation[playerAction][aniIndex], (int)x, (int)y, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT,null);
    }

    private void updateAnimation() {
        aniTick++;
        if(aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= PlayerValues.INDEX_SPRITE) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    public void setAnimation() {

        int startAni = playerAction;

        if (moving) {
            playerAction = PlayerValues.RUN;
        } else {
            playerAction = PlayerValues.IDLE;
        }

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void loadAnimations() {
        BufferedImage img = ResourceFuncUtilities.loadSources("knight");
        animation = new BufferedImage[4][6];
        for (int j = 0; j < animation.length; j++) {
            for (int i = 0; i < animation[j].length; i++) {
                animation[j][i] = img.getSubimage(i * Player.PLAYER_WIDTH, j * Player.PLAYER_HEIGHT, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
            }
        }
    }

}
