package it.unibo.knightreasures;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.view.impl.LevelImpl;
import it.unibo.knightreasures.view.impl.LevelManagerImpl;

public class LevelAndImageTest {

    private GameplayImpl gameplay;
    private LevelManagerImpl levelManager;
    private LevelImpl level;

    @BeforeEach
    void setUp() {
        gameplay = new GameplayImpl(null);
        levelManager = gameplay.getLevel();
        level = levelManager.getCurrentLevel();
    }

    @Test
    void testImagesAreLoaded() {
        BufferedImage background = ResourceFuncUtilities.loadSources(Images.BACKGROUND);
        BufferedImage playerImage = ResourceFuncUtilities.loadSources(Images.PLAYER);
        BufferedImage skeletonImage = ResourceFuncUtilities.loadSources(Images.SKELETON);
        BufferedImage treasureImage = ResourceFuncUtilities.loadSources(Images.TREASURE);

        assertNotNull(background);
        assertNotNull(playerImage);
        assertNotNull(skeletonImage);
        assertNotNull(treasureImage);
    }

    @Test
    void testLevelHasEnemies() {
        assertFalse(level.getSkeletons().isEmpty());
    }

    @Test
    void testLevelHasTreasures() {
        assertFalse(level.getTreasures().isEmpty());
    }

    @Test
    void testLevelHasChests() {
        assertFalse(level.getChests().isEmpty());
    }

    @Test
    void testLevelHasSpikes() {
        assertFalse(level.getSpikes().isEmpty());
    }

    @Test
    void testLevelHasPlayerSpawn() {
        assertNotNull(level.getPlayerSpawn());
    }

    @Test
    void testLevelDataIsValid() {
        int[][] levelData = level.getLevelData();
        assertNotNull(levelData);
        assertTrue(levelData.length > 0);
        assertTrue(levelData[0].length > 0);
    }

    @Test
    void testObjectsAreCorrectlyLoaded() {
        List<?> objects = levelManager.getCurrentLevel().getTreasures();
        assertNotNull(objects);
        assertFalse(objects.isEmpty());
    }
}
