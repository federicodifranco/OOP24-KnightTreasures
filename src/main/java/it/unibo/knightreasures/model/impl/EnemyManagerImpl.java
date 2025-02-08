package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.model.api.EnemyManager;
import it.unibo.knightreasures.utilities.ModelConstants.SkeletonsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;
import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Manages enemies in the game, including their state, rendering, and updates.
 */
public final class EnemyManagerImpl implements EnemyManager {

    private final GameplayImpl playing;
    private BufferedImage[][] skeletonArr;
    private List<SkeletonImpl> skeletons = new ArrayList<>();

    /**
     * Constructs the enemy manager and initializes enemy images.
     *
     * @param playing the gameplay instance managing the game state.
     */
    public EnemyManagerImpl(final GameplayImpl playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    /**
     * Loads skeleton enemy images from resources.
     */
    private void loadEnemyImgs() {
        skeletonArr = new BufferedImage[SkeletonsValues.SKELETON_STATES][SkeletonsValues.SKELETON_ANIMATION_FRAMES];
        final BufferedImage temp = ResourceFuncUtilities.loadSources(Images.SKELETON);
        for (int j = 0; j < skeletonArr.length; j++) {
            for (int i = 0; i < skeletonArr[j].length; i++) {
                skeletonArr[j][i] = temp.getSubimage(
                    i * Skeletons.WIDTH_DEFAULT,
                    j * Skeletons.HEIGHT_DEFAULT,
                    Skeletons.WIDTH_DEFAULT,
                    Skeletons.HEIGHT_DEFAULT
                );
            }
        }
    }

    /**
     * Loads enemy instances into the game.
     */
    @Override
    public void addEnemies(final LevelImpl level) {
        skeletons = level.getSkeletons();
    }

    /**
     * Updates all enemies in the game.
     *
     * @param lvlData the level data containing collision information.
     */
    @Override
    public void update(final int[][] lvlData, final PlayerEntityImpl player) {
        boolean isAnyActive = false;
        for (final SkeletonImpl skt : skeletons) {
            if (skt.isActive()) {
                skt.update(lvlData, player);
                isAnyActive = true;
            }
        }
        if (!isAnyActive) {
            playing.setLevelCompleted(true);
        }
    }

    /**
     * Draws all enemies.
     *
     * @param g          the graphics object used for rendering.
     * @param xLvlOffset the level's x-axis offset.
     */
    @Override
    public void draw(final Graphics g, final int xLvlOffset) {
        drawSkeletons(g, xLvlOffset);
    }

    @Override
    public void checkEnemyHit(final Rectangle2D.Float attackBox) {
        for (final SkeletonImpl skt : skeletons) {
            if (skt.isActive() && attackBox.intersects(skt.getHitbox()) && skt.getCurrentHealth() > 0) {
                skt.hurt(SkeletonsValues.DAMAGE);
                break;
            }
        }
    }

    /**
     * Draws all skeletons in the game.
     *
     * @param g          the graphics object used for rendering.
     * @param xLvlOffset the level's x-axis offset.
     */
    @Override
    public void drawSkeletons(final Graphics g, final int xLvlOffset) {
        for (final SkeletonImpl skt : skeletons) {
             if (skt.isActive()) {
                g.drawImage(skeletonArr[skt.getEnemyState()][skt.getAniIndex()], 
                (int) skt.getHitbox().x - xLvlOffset - Skeletons.DRAW_OFFSET_X + skt.flipX(), 
                (int) skt.getHitbox().y - Skeletons.DRAW_OFFSET_Y, 
                Skeletons.WIDTH * skt.flipW(), 
                Skeletons.HEIGHT, null);
            }
        }
    }

    @Override
    public void resetAllEnemies() {
        for (final SkeletonImpl skt : skeletons) {
            skt.resetEnemy();
        }
    }

    @Override
    public boolean hasActiveEnemies() {
        for (final SkeletonImpl skt : skeletons) {
            if (skt.isActive()) {
                return true;
            }
        }
        return false;
    }
}
