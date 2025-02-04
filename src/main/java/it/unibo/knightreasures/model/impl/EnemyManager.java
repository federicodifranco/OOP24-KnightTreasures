package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import it.unibo.knightreasures.heart.core.impl.Gameplay;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Skeletons;

public class EnemyManager {

    private Gameplay playing;
    private BufferedImage[][] skeletonArr;
    private ArrayList<Skeleton> skeletons = new ArrayList<>();

    public EnemyManager(Gameplay playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {
        skeletons = ResourceFuncUtilities.getSkeletons();
    }

    public void update(int[][] lvlData) {
        for (Skeleton skt : skeletons) skt.update(lvlData);
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawSkeletons(g, xLvlOffset);
    }

    public void drawSkeletons(Graphics g, int xLvlOffset) {
        for (Skeleton skt : skeletons) {
            g.drawImage(skeletonArr[skt.getEnemyState()][skt.getIndex()], (int) skt.getHitbox().x - xLvlOffset - Skeletons.DRAW_OFFSET_X, (int) skt.getHitbox().y - Skeletons.DRAW_OFFSET_Y, Skeletons.WIDTH, Skeletons.HEIGHT, null);
            skt.drawHitbox(g, xLvlOffset);
        }
    }


    private void loadEnemyImgs() {
        skeletonArr = new BufferedImage[5][11];
        BufferedImage temp = ResourceFuncUtilities.loadSources(Images.SKELETON);
        for (int j = 0; j < skeletonArr.length; j++) {
            for (int i = 0; i < skeletonArr[j].length; i++) {
                skeletonArr[j][i] = temp.getSubimage(i * Skeletons.WIDTH_DEFAULT, j * Skeletons.HEIGHT_DEFAULT, Skeletons.WIDTH_DEFAULT, Skeletons.HEIGHT_DEFAULT);
            }
        }
    }
}
