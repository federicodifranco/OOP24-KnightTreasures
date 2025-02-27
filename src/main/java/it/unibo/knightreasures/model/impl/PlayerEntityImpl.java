package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.api.PlayerEntity;
import it.unibo.knightreasures.utilities.HelpMethods;
import it.unibo.knightreasures.utilities.ModelConstants.Application;
import it.unibo.knightreasures.utilities.ModelConstants.PlayerValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Physics;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.impl.HeartsImpl;

/**
 * Represents the player entity in the game, handling movement, animations, and
 * interactions with the game world.
 */
public final class PlayerEntityImpl extends EntityManagerImpl implements PlayerEntity {

    private BufferedImage[][] animation;
    private final HeartsImpl hearts;
    private final GameplayImpl playing;
    private int aniTick, aniIndex;
    private int flipX = PlayerValues.FLIPX_DEFAULT;
    private int flipW = PlayerValues.FLIPW_DEFAULT;
    private int playerOffsetX = PlayerValues.OFFSET_X_DEFAULT;
    private int playerAction = PlayerValues.IDLE;
    private int[][] lvlData;
    private boolean moving, attacking, attackChecked, inAir;
    private boolean left, right, up, down, jump;
    private float airSpeed;
    private Rectangle2D.Float attackbox;

    /**
     * Constructs a new PlayerEntity with the specified parameters.
     *
     * @param x the initial x-coordinate of the player.
     * @param y the initial y-coordinate of the player.
     * @param width the width of the player entity.
     * @param height the height of the player entity.
     * @param playing the current gameplay.
     * @param hearts the hearts of the player entity.
     */
    public PlayerEntityImpl(final float x, final float y, final int width, final int height,
                            final GameplayImpl playing, final HeartsImpl hearts) {
        super(x, y, width, height);
        this.playing = playing;
        this.hearts = hearts;
        this.attackbox = getAttackbox();
        loadAnimations();
        initHitBox(Player.HITBOX_WIDTH, Player.HITBOX_HEIGHT);
        initAttackBox();
    }

    private void initAttackBox() {
        attackbox = new Rectangle2D.Float(getX(), getY(), Player.ATTACKBOX_WIDTH, Player.ATTACKBOX_HEIGHT);
    }

    private void checkAttack() {
        if (attackChecked || aniIndex != PlayerValues.ATTACK_INDEX) {
            return;
        }
        attackChecked = true;
        playing.checkEnemyHit(attackbox);
    }

    private void updateAttackBox() {
        if (right && left) {
            if (flipW == PlayerValues.FLIPW_DEFAULT) {
                attackbox.x = getHitbox().x + getHitbox().width + Player.ATTACKBOX_OFFSET;
            } else {
                attackbox.x = getHitbox().x - getHitbox().width - Player.ATTACKBOX_OFFSET;
            }
        } else if (right) {
            attackbox.x = getHitbox().x + getHitbox().width + Player.ATTACKBOX_OFFSET;
        } else if (left) {
            attackbox.x = getHitbox().x - getHitbox().width - Player.ATTACKBOX_OFFSET;
        }
        attackbox.y = getHitbox().y + Player.ATTACKBOX_OFFSET;
    }

    private void checkChestOpened() {
        this.playing.checkChestOpened(getHitbox());
    }

    private void checkSpikeTouched() {
        this.playing.checkSpikeTouched(this, hearts);
    }

    private void checkCollectedOpened() {
        this.playing.checkCollectTreasure(getHitbox());
    }

    /**
     * Updates the player's state, including movement and animations.
     */
    @Override
    public void update() {
        if (hearts.getCurrentHearts() == 0) {
            playing.setGameOver(true);
            return;
        }
        updatePosition();
        checkCollectedOpened();
        checkChestOpened();
        checkSpikeTouched();
        if (attacking) {
            checkAttack();
        }
        updateAnimation();
        updateAttackBox();
        setAnimation();
    }

    /**
     * Updates the player's animation based on state changes.
     */
    private void updateAnimation() {
        aniTick++;
        if (aniTick >= Application.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= PlayerValues.INDEX_SPRITE) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    /**
     * Makes the player jump if they are not already in the air.
     */
    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = Physics.JUMP_SPEED;
    }

    /**
     * Resets the player's in-air state and velocity.
     */
    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    /**
     * Updates the player's x-position based on movement and collision
     * detection.
     *
     * @param xSpeed the speed at which the player moves horizontally.
     */
    private void updateXPos(final float xSpeed) {
        if (HelpMethods.canMoveHere(getHitbox().x + xSpeed, getHitbox().y, getHitbox().width, getHitbox().height, lvlData)) {
            getHitbox().x += xSpeed;
        } else {
            getHitbox().x = HelpMethods.getEntityXPosNextToWall(getHitbox(), xSpeed);
        }
    }

    /**
     * Resets the animation tick and index.
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Loads the player's animation sprites.
     */
    private void loadAnimations() {
        final BufferedImage playerImg = ResourceFuncUtilities.loadSources(Images.PLAYER);
        animation = new BufferedImage[PlayerValues.SPRITES_ROWS][PlayerValues.SPRITES_COLUMNS];
        for (int j = 0; j < animation.length; j++) {
            for (int i = 0; i < animation[j].length; i++) {
                animation[j][i] = playerImg.getSubimage(i * Player.PLAYER_WIDTH, j * Player.PLAYER_HEIGHT,
                        Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
            }
        }
    }

    @Override
    public void render(final Graphics g, final int lvlOffset) {
        g.drawImage(animation[playerAction][aniIndex],
                (int) (getHitbox().x - Player.X_DRAW_OFFSET) - lvlOffset + flipX - playerOffsetX,
                (int) (getHitbox().y - Player.Y_DRAW_OFFSET),
                this.getWidth() * flipW, this.getHeight(), null);
    }

    @Override
    public void updatePosition() {
        moving = false;
        if (jump) {
            jump();
        }
        if (!inAir && (!left && !right || left && right)) {
            return;
        }
        float xSpeed = 0;
        if (left) {
            xSpeed -= Physics.SPEED;
            flipX = getWidth();
            flipW = -PlayerValues.FLIPW_DEFAULT;
            playerOffsetX = Player.HITBOX_X_OFFSET;
            setX(getHitbox().x - playerOffsetX);
        }
        if (right) {
            xSpeed += Physics.SPEED;
            flipX = PlayerValues.FLIPX_DEFAULT;
            flipW = PlayerValues.FLIPW_DEFAULT;
            playerOffsetX = PlayerValues.OFFSET_X_DEFAULT;
            setX(getHitbox().x);
        }
        if (!inAir && !HelpMethods.isEntityOnFloor(getHitbox(), lvlData)) {
            inAir = true;
        }
        if (inAir) {
            if (HelpMethods.canMoveHere(getHitbox().x,
                    getHitbox().y + airSpeed,
                    getHitbox().width,
                    getHitbox().height,
                    lvlData)) {
                getHitbox().y += airSpeed;
                airSpeed += Physics.GRAVITY;
                updateXPos(xSpeed);
            } else {
                getHitbox().y = HelpMethods.getEntityYPosNextToWall(getHitbox(), airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = Physics.FALL_SPEED;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
    }

    @Override
    public void loseHeart() {
        final int currentHearts = hearts.getCurrentHearts();
        if (currentHearts > 0) {
            hearts.setCurrentHearts(currentHearts - PlayerValues.DAMAGE);
        }
        if (currentHearts < 0) {
            hearts.setCurrentHearts(PlayerValues.NO_LIVES);
        }
    }

    @Override
    public int getLives() {
        return hearts.getCurrentHearts();
    }

    @Override
    public void updateLives() {
        hearts.setCurrentHearts(PlayerValues.NUM_LIVES);
    }

    @Override
    public void setAnimation() {
        final int startAni = playerAction;
        if (moving) {
            playerAction = PlayerValues.RUN;
        } else {
            playerAction = PlayerValues.IDLE;
        }
        if (inAir && airSpeed < 0) {
            playerAction = PlayerValues.JUMP;
        }
        if (attacking) {
            playerAction = PlayerValues.ATTACK;
        }
        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    @Override
    public void loadLvlData(final int[][] lvlData) {
        this.lvlData = lvlData.clone();
        if (!HelpMethods.isEntityOnFloor(getHitbox(), lvlData)) {
            inAir = true;
        }
    }

    @Override
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    @Override
    public void setJump(final boolean jump) {
        this.jump = jump;
    }

    @Override
    public void setAttacking(final boolean attacking) {
        this.attacking = attacking;
    }

    @Override
    public boolean isLeft() {
        return left;
    }

    @Override
    public void setLeft(final boolean left) {
        this.left = left;
    }

    @Override
    public boolean isRight() {
        return right;
    }

    @Override
    public void setRight(final boolean right) {
        this.right = right;
    }

    @Override
    public boolean isUp() {
        return up;
    }

    @Override
    public void setUp(final boolean up) {
        this.up = up;
    }

    @Override
    public boolean isDown() {
        return down;
    }

    @Override
    public void setDown(final boolean down) {
        this.down = down;
    }

    @Override
    public boolean inAir() {
        return inAir;
    }

    @Override 
    public boolean isJumping() {
        return jump;
    }

    @Override
    public void resetAll() {
        resetDirBooleans();
        updateLives();
        setState(PlayerValues.IDLE);
        inAir = true;
        attacking = false;
        moving = false;
        jump = false;
        airSpeed = PlayerValues.AIR_SPEED_DEFAULT;
        flipW = PlayerValues.FLIPW_DEFAULT;
        flipX = PlayerValues.FLIPX_DEFAULT;
        playerOffsetX = PlayerValues.OFFSET_X_DEFAULT;
        getHitbox().x = getX();
        getHitbox().y = getY();
        if (!HelpMethods.isEntityOnFloor(getHitbox(), lvlData)) {
            inAir = true;
        }
    }

    @Override
    public void setSpawn(final Point spawn) {
        setX(spawn.x);
        setY(spawn.y);
        getHitbox().x = getX();
        getHitbox().y = getY();
    }
}
