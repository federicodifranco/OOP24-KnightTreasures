package it.unibo.knightreasures.model.impl;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.ModelConstants.ObjectsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.ObjectConstants;
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelImpl;
import it.unibo.knightreasures.view.impl.LevelManagerImpl;

public class ObjectManagerImpl {

    private final GameplayImpl playing;
    private final LevelManagerImpl level;
    private BufferedImage[][] treasureImgs, chestImgs;
    private BufferedImage spikeImgs;
    private List<TreasureImpl> treasures = new ArrayList<>();
    private List<ChestImpl> chests = new ArrayList<>();
    private List<SpikeImpl> spikes = new ArrayList<>();
    private int collectedTreasure = ObjectsValues.INITIAL_COLLECTED_TREASURES;

    public ObjectManagerImpl(GameplayImpl playing, LevelManagerImpl level) {
        this.playing = playing;
        this.level = level;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage treasureImg = ResourceFuncUtilities.loadSources(Images.TREASURE);
        treasureImgs = new BufferedImage[ObjectsValues.TREASURE_SPRITES_ROWS][ObjectsValues.TREASURE_SPRITES_COLUMNS];

        for (int j = 0; j < treasureImgs.length; j++) {
            for (int i = 0; i < treasureImgs[j].length; i++) {
                treasureImgs[j][i] = treasureImg.getSubimage(ObjectConstants.TREASURE_WIDTH_DEFAULT * i, ObjectConstants.TREASURE_HEIGHT_DEFAULT * j, ObjectConstants.TREASURE_WIDTH_DEFAULT, ObjectConstants.TREASURE_HEIGHT_DEFAULT);
            }
        }

        BufferedImage chestImg = ResourceFuncUtilities.loadSources(Images.CHEST);
        chestImgs = new BufferedImage[ObjectsValues.CHEST_SPRITES_ROWS][ObjectsValues.CHEST_SPRITES_COLUMNS];

        for (int j = 0; j < chestImgs.length; j++) {
            for (int i = 0; i < chestImgs[j].length; i++) {
                chestImgs[j][i] = chestImg.getSubimage(ObjectConstants.CHEST_WIDTH_DEFAULT * i, ObjectConstants.CHEST_HEIGHT_DEFAULT * j, ObjectConstants.CHEST_WIDTH_DEFAULT, ObjectConstants.CHEST_HEIGHT_DEFAULT);
            }
        }

        spikeImgs = ResourceFuncUtilities.loadSources(Images.SPIKE);
    }

    private void drawChests(Graphics g, int xLvlOffset) {
        for (ChestImpl c : chests) {
            if (c.isActive()) {
                g.drawImage(chestImgs[ObjectsValues.CHEST_IMAGES_INDEX][c.getAniIndex()],
                        (int) (c.getHitbox().x - c.getXOffset() - ObjectConstants.CHEST_X_SHIFT - xLvlOffset),
                        (int) (c.getHitbox().y - c.getYOffset() - ObjectConstants.CHEST_Y_SHIFT),
                        ObjectConstants.CHEST_WIDTH,
                        ObjectConstants.CHEST_HEIGHT,
                        null);
            }
        }
    }

    private void drawTreasures(Graphics g, int xLvlOffset) {
        for (TreasureImpl t : treasures) {
            if (t.isActive()) {
                g.drawImage(treasureImgs[t.getObjType()][t.getAniIndex()],
                        (int) (t.getHitbox().x - ObjectConstants.TREASURE_X_OFFSET - xLvlOffset),
                        (int) (t.getHitbox().y - ObjectConstants.TREASURE_Y_OFFSET),
                        ObjectConstants.TREASURE_WIDTH,
                        ObjectConstants.TREASURE_HEIGHT,
                        null);
            }
        }
    }

    private void drawSpikes(Graphics g, int xLvlOffset) {
        for (SpikeImpl s : spikes) {
            g.drawImage(spikeImgs,
                    (int) (s.getHitbox().x - xLvlOffset),
                    (int) (s.getHitbox().y - s.getYOffset()),
                    ObjectConstants.SPIKE_WIDTH,
                    ObjectConstants.SPIKE_HEIGHT,
                    null);
        }
    }

    public void update() {
        for (TreasureImpl t : treasures) {
            if (t.isActive()) {
                t.update();
            }
        }

        for (ChestImpl c : chests) {
            if (c.isActive()) {
                c.update();
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawTreasures(g, xLvlOffset);
        drawChests(g, xLvlOffset);
        drawSpikes(g, xLvlOffset);
    }

    public void checkSpikeTouched(PlayerEntityImpl player, HeartsImpl hearts) {
        for (SpikeImpl spike : spikes) {
            if (spike.getHitbox().intersects(player.getHitbox())) {
                hearts.setCurrentHearts(player.getCurrentHealth() - ObjectsValues.SPIKE_DAMAGE);
            }
        }
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (TreasureImpl t : treasures) {
            if (t.isActive() && hitbox.intersects(t.getHitbox())) {
                t.setActive(false);
                collectedTreasure++;
            }
        }
    }

    public void checkChestOpened(Rectangle2D.Float hitbox) {
        for (ChestImpl c : chests) {
            if (c.isActive() && !c.isOpened() && c.getHitbox().intersects(hitbox)) {
                c.setAnimation(true);
                c.setOpened(true);
                treasures.add(new TreasureImpl(
                        (int) (c.getHitbox().x + ObjectConstants.TREASURE_X_SHIFT),
                        (int) (c.getHitbox().y - ObjectConstants.TREASURE_Y_SHIFT),
                        ObjectsValues.LEVEL_TREASURES[level.getLevelIndex()]
                ));
                return;
            }
        }
    }

    public void loadObjects(LevelImpl newLevel) {
        treasures = new ArrayList<>(newLevel.getTreasures());
        chests = new ArrayList<>(newLevel.getChests());
        spikes = newLevel.getSpikes();
    }

    public void resetAllObjects() {
        loadObjects(playing.getLevel().getCurrentLevel());
        for (TreasureImpl t : treasures) {
            t.reset();
        }

        for (ChestImpl c : chests) {
            c.reset();
        }
        collectedTreasure = ObjectsValues.INITIAL_COLLECTED_TREASURES;
    }

    public int isAllCollectedTreasures() {
        return collectedTreasure;
    }
}