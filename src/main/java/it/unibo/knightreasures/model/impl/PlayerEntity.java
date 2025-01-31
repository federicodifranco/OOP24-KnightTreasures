package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.Physics;
import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Player;

public class PlayerEntity extends EntityManager {

    private BufferedImage[][] animation;
    private int aniTick, aniIndex;
    private int playerAction = PlayerValues.IDLE;
    private boolean moving = false, attacking = false;
    private boolean up, down, right, left;
    private int [][] lvlData;

    public PlayerEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update() {
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animation[playerAction][aniIndex], (int) (hitBox.x - Player.X_DRAW_OFFSET), (int) (hitBox.y - Player.Y_DRAW_OFFSET), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT, null);
        drawHitbox(g);
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= PlayerValues.INDEX_SPRITE) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    public void updatePosition() {

        moving = false;

        if (!left && !right && !up && !down) return;

        float xSpeed = 0, ySpeed = 0;

        if (left && !right) {
            xSpeed -= Physics.SPEED;
        } else if (right && !right) {
            xSpeed += Physics.SPEED;
        }

        if (up && !down) {
            ySpeed -= Physics.SPEED;
        } else if (!up && down) {
            ySpeed += Physics.SPEED;
        }

        if (HelpMethods.CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }
    }

    public void setAnimation() {

        int startAni = playerAction;

        if (moving) {
            playerAction = PlayerValues.RUN;
        } else {
            playerAction = PlayerValues.IDLE;
        }
        if (attacking) {
            playerAction = PlayerValues.ATTACK;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void loadAnimations() {
        BufferedImage playerImg = ResourceFuncUtilities.loadSources(Images.PLAYER);
        animation = new BufferedImage[PlayerValues.SPRITES_ROWS][PlayerValues.SPRITES_COLUMNS];
        for (int j = 0; j < animation.length; j++) {
            for (int i = 0; i < animation[j].length; i++) {
                animation[j][i] = playerImg.getSubimage(i * Player.PLAYER_WIDTH, j * Player.PLAYER_HEIGHT, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
            }
        }
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

}
