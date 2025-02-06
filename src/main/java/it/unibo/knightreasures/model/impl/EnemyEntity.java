package it.unibo.knightreasures.model.impl;

import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Physics;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Represents an enemy entity in the game.
 */
public abstract class EnemyEntity extends EntityManager {

    protected int aniIndex, aniTick, enemyState, tileY, walkDir = Directions.LEFT;
    protected boolean firstUpdate = true, inAir, attackChecked, active = true;
    protected float attackDistance = Window.TILES_SIZE, fallSpeed;

    public EnemyEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
        initHitBox(width, height);
        maxHealth = SkeletonsValues.NUM_LIVES;
        currentHealth = maxHealth;
    }

    protected void firstUpdateCheck(int[][] lvlData) {
        if (firstUpdate) {
            if (!HelpMethods.isEntityOnFloor(getHitbox(), lvlData)) {
                setInAir(true);
            }
            firstUpdate = false;
        }
    }

    public void hurt(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) newState(SkeletonsValues.DIE);
        else newState(SkeletonsValues.HURT);
    }

    public boolean isActive() {
        return active;
    }

    protected void checkPlayerHit(Rectangle2D.Float attackBox, PlayerEntity player) {
        if (attackBox.intersects(player.getHitbox())) player.loseHeart();
        attackChecked = true;
    }

    protected void updateInAir(int[][] lvlData) {
        if (HelpMethods.canMoveHere(getHitbox().x, getHitbox().y + fallSpeed, getHitbox().width, getHitbox().height, lvlData)) {
            getHitbox().y += fallSpeed;
            fallSpeed += Physics.GRAVITY;
        } else {
            setInAir(false);
            getHitbox().y = HelpMethods.getEntityYPosNextToWall(getHitbox(), fallSpeed);
            tileY = (int) (getHitbox().y / Window.TILES_SIZE);
        }
    }

    protected void move(int[][] lvlData) {
        float xSpeed = 0;
        if (walkDir == Directions.LEFT) {
            xSpeed = -Skeletons.SPEED;
            if (HelpMethods.canMoveHere(getHitbox().x + xSpeed, getHitbox().y, getHitbox().width, getHitbox().height, lvlData)) {
                if (HelpMethods.isFloor(getHitbox(), xSpeed, lvlData)) {
                    getHitbox().x += xSpeed;
                    return;
                }
            }
        } else {
            xSpeed = Skeletons.SPEED;
            if (HelpMethods.canMoveHere(getHitbox().x + xSpeed, getHitbox().y, getHitbox().width, getHitbox().height, lvlData)) {
                Rectangle2D.Float rightEdgeBox = new Rectangle2D.Float(getHitbox().x + getHitbox().width, getHitbox().y, 1 , getHitbox().height);
                if (HelpMethods.isFloor(rightEdgeBox, xSpeed, lvlData)) {
                    getHitbox().x += xSpeed;
                    return;
                }
            }
        }
        changeWalkDir();
    }

    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }

    protected void turnTowardsPlayer(PlayerEntity player) {
        if (player.getHitbox().x > getHitbox().x) {
            walkDir = Directions.RIGHT;
        } else walkDir = Directions.LEFT;
    }

    protected boolean canSeePlayer(int[][] lvlData, PlayerEntity player) {
        int playerTileY = (int) (player.getHitbox().y / Window.TILES_SIZE);
        if (playerTileY == tileY) {
            if (isPlayerInRange(player)) {
                if (HelpMethods.isSightClear(lvlData, getHitbox(), player.getHitbox(), tileY)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isPlayerInRange(PlayerEntity player) {
        int absValue = (int) Math.abs(player.getHitbox().x - getHitbox().x);
        return absValue <= attackDistance * 5;
    }

    protected boolean isPlayerCloseForAttack(PlayerEntity player) {
        int absValue = (int) Math.abs(player.getHitbox().x - getHitbox().x);
        return absValue <= attackDistance;
    }

    private int getSpriteAmount(final int enemyState) {
        switch (enemyState) {
            case SkeletonsValues.IDLE:
                return SkeletonsValues.IDLE_SPRITES;
                case SkeletonsValues.RUN:
                return SkeletonsValues.RUN_SPRITES;
                case SkeletonsValues.ATTACK:
                return SkeletonsValues.ATTACK_SPRITES;
                case SkeletonsValues.HURT:
                return SkeletonsValues.HURT_SPRITES;
                case SkeletonsValues.DIE:
                return SkeletonsValues.DIE_SPRITES;
            default:
               return SkeletonsValues.IDLE_SPRITES;
        }
    }

    protected void updateAnimation() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(enemyState)) {
                aniIndex = 0;
                switch(enemyState) {
                    case SkeletonsValues.ATTACK, SkeletonsValues.HURT -> enemyState = SkeletonsValues.IDLE;
                    case SkeletonsValues.DIE -> active = false;
                }
            }
        }
    }

    protected void changeWalkDir() {
        if (walkDir == Directions.LEFT) {
            walkDir = Directions.RIGHT;
        } else {
            walkDir = Directions.LEFT;
        }
    }

    @Override
    public int getIndex() {
        return aniIndex;
    }

    public void resetEnemy() {
        getHitbox().x = getX();
        getHitbox().y = getY();
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(SkeletonsValues.IDLE);
        active = true;
        setAirSpeed(0);
    }

    public int getEnemyState() {
        return enemyState;
    }

}