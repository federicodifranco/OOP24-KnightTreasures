package it.unibo.knightreasures;

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

/**
 * Test per verificare il corretto caricamento delle immagini e dei livelli di gioco.
 */
class LevelAndImageTest {

    private LevelImpl level;

    /**
     * Inizializza gli oggetti prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        final GameplayImpl gameplay = new GameplayImpl(null);
        final LevelManagerImpl levelManager = gameplay.getLevel();
        level = levelManager.getCurrentLevel();
    }

    /**
     * Verifica che tutte le immagini del gioco siano correttamente caricate.
     */
    @Test
    void testImagesAreLoaded() {
        assertNotNull(ResourceFuncUtilities.loadSources(Images.BACKGROUND));
        assertNotNull(ResourceFuncUtilities.loadSources(Images.PLAYER));
        assertNotNull(ResourceFuncUtilities.loadSources(Images.SKELETON));
        assertNotNull(ResourceFuncUtilities.loadSources(Images.TREASURE));
    }

    /**
     * Verifica che il livello contenga nemici.
     */
    @Test
    void testLevelHasEnemies() {
        assertFalse(level.getSkeletons().isEmpty());
    }

    /**
     * Verifica che il livello contenga tesori.
     */
    @Test
    void testLevelHasTreasures() {
        assertFalse(level.getTreasures().isEmpty());
    }

    /**
     * Verifica che il livello contenga bauli.
     */
    @Test
    void testLevelHasChests() {
        assertFalse(level.getChests().isEmpty());
    }

    /**
     * Verifica che il livello contenga trappole a spuntoni.
     */
    @Test
    void testLevelHasSpikes() {
        assertFalse(level.getSpikes().isEmpty());
    }

    /**
     * Verifica che il punto di spawn del player sia correttamente inizializzato.
     */
    @Test
    void testLevelHasPlayerSpawn() {
        assertNotNull(level.getPlayerSpawn());
    }

    /**
     * Verifica che i dati della mappa del livello siano validi.
     */
    @Test
    void testLevelDataIsValid() {
        final int[][] levelData = level.getLevelData();
        assertNotNull(levelData);
        assertTrue(levelData.length > 0);
        assertTrue(levelData[0].length > 0);
    }

    /**
     * Verifica che gli oggetti del livello siano correttamente caricati.
     */
    @Test
    void testObjectsAreCorrectlyLoaded() {
        final List<?> objects = level.getTreasures();
        assertNotNull(objects);
        assertFalse(objects.isEmpty());
    }
}
