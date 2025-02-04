package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import it.unibo.knightreasures.heart.core.impl.Gameplay;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

/**
 * Manages enemies in the game, including their state, rendering, and updates.
 */
public final class EnemyManager {

    private static final int SKELETON_STATES = 5;
    private static final int SKELETON_ANIMATION_FRAMES = 11;

    private final Gameplay playing;
    private BufferedImage[][] skeletonArr;
    private ArrayList<Skeleton> skeletons = new ArrayList<>();

    /**
     * Constructs the enemy manager and initializes enemy images.
     *
     * @param playing the gameplay instance managing the game state.
     */
    public EnemyManager(final Gameplay playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    /**
     * Loads enemy instances into the game.
     */
    private void addEnemies() {
        skeletons = ResourceFuncUtilities.getSkeletons();
    }

    /**
     * Updates all enemies in the game.
     *
     * @param lvlData the level data containing collision information.
     */
    public void update(final int[][] lvlData) {
        for (final Skeleton skt : skeletons) {
            skt.update(lvlData);
        }
    }

    /**
     * Draws all enemies.
     *
     * @param g          the graphics object used for rendering.
     * @param xLvlOffset the level's x-axis offset.
     */
    public void draw(final Graphics g, final int xLvlOffset) {
        drawSkeletons(g, xLvlOffset);
    }

    /**
     * Draws all skeletons in the game.
     *
     * @param g          the graphics object used for rendering.
     * @param xLvlOffset the level's x-axis offset.
     */
    public void drawSkeletons(final Graphics g, final int xLvlOffset) {
        for (final Skeleton skt : skeletons) {
            g.drawImage(
                skeletonArr[skt.getEnemyState()][skt.getIndex()],
                (int) skt.getHitbox().x - xLvlOffset - Skeletons.DRAW_OFFSET_X,
                (int) skt.getHitbox().y - Skeletons.DRAW_OFFSET_Y,
                Skeletons.WIDTH, Skeletons.HEIGHT,
                null
            );
            skt.drawHitbox(g, xLvlOffset);
        }
    }

    /**
     * Loads skeleton enemy images from resources.
     */
    private void loadEnemyImgs() {
        skeletonArr = new BufferedImage[SKELETON_STATES][SKELETON_ANIMATION_FRAMES];
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
}