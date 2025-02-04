package it.unibo.knightreasures.model.impl;

import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.Directions;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Physics;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

public class EnemyEntity extends EntityManager {

    private int aniIndex, enemyState, enemyType;
    private int aniTick;
    private boolean firstUpdate = true, inAir;
    private float fallSpeed;
    private int walkDir = Directions.LEFT;

    public EnemyEntity(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitBox(x, y, width, height);
    }

    private int getSpriteAmount(final int enemyType, final int enemyState) {
        switch (enemyState) {
            case SkeletonsValues.IDLE:
                return SkeletonsValues.IDLE_SPRITES;
            case SkeletonsValues.RUN:
                return SkeletonsValues.RUN_SPRITES;
            case SkeletonsValues.HURT:
                return SkeletonsValues.HURT_SPRITES;
            case SkeletonsValues.DIE:
                return SkeletonsValues.DIE_SPRITES;
        }
        return enemyState;
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimation();
    }

    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            if (!HelpMethods.isEntityOnFloor(getHitbox(), lvlData))
                inAir = true;
            firstUpdate = false;
        }
        if (inAir) {
            if (HelpMethods.canMoveHere(getHitbox().x, getHitbox().y + fallSpeed, getHitbox().width, getHitbox().height, lvlData)) {
                getHitbox().y += fallSpeed;
                fallSpeed += Physics.GRAVITY;
            } else {
                inAir = false;
                getHitbox().y = HelpMethods.getEntityYPosNextToWall(getHitbox(), fallSpeed);
            }
        } else {
            switch (enemyState) {
                case SkeletonsValues.IDLE:
                    enemyState = SkeletonsValues.RUN;
                    break;
                case SkeletonsValues.RUN:
                    float xSpeed = 0;
                    if (walkDir == Directions.LEFT) {
                        xSpeed = -Skeletons.SPEED;
                    } else
                        xSpeed = Skeletons.SPEED;
                    if (HelpMethods.canMoveHere(getHitbox().x + xSpeed, getHitbox().y, getHitbox().width, getHitbox().height, lvlData)) {
                        if (HelpMethods.isFloor(getHitbox(), xSpeed, lvlData)) {
                            getHitbox().x += xSpeed;
                            return;
                        }
                    }
                    changeWalkDir();
                    break;
            }
        }
    }

    private void changeWalkDir() {
        if (walkDir == Directions.LEFT) {
            walkDir = Directions.RIGHT;
        } else
            walkDir = Directions.LEFT;
    }

    public int getIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    @Override
    public void update() {
    }
}
