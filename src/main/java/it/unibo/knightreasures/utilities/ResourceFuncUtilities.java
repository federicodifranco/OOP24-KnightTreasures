package it.unibo.knightreasures.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;

/**
 * Utility class for handling resource-related operations, such as loading images and creating levels.
 * <p>This class cannot be instantiated or extended.</p>
 */
public final class ResourceFuncUtilities {

    private static final Logger LOGGER = Logger.getLogger(ResourceFuncUtilities.class.getName());

    // Private constructor to prevent instantiation
    private ResourceFuncUtilities() {
    }

    /**
     * Loads an image from the resources folder as an {@link Image}.
     *
     * @param fileName The name of the image file (without extension).
     * @return The loaded image, or null if loading fails.
     */
    public static Image getSources(final String fileName) {
        return new ImageIcon("src/main/resources/" + fileName + ".png").getImage();
    }

    /**
     * Loads an image from the resources folder as a {@link BufferedImage}.
     *
     * @param fileName The name of the image file (without extension).
     * @return The loaded BufferedImage, or null if loading fails.
     */
    public static BufferedImage getBufferedSources(final String fileName) {
        final File file = new File("src/main/resources/" + fileName + ".png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return img;
    }

    /**
     * Loads a BufferedImage from the classpath resources.
     *
     * @param fileName The name of the image file (without extension).
     * @return The loaded BufferedImage, or null if loading fails.
     */
    public static BufferedImage loadSources(final String fileName) {
        BufferedImage img = null;
        try (InputStream s = ResourceFuncUtilities.class.getResourceAsStream("/" + fileName + ".png")) {
            if (s == null) {
                LOGGER.log(Level.SEVERE, "File not found: {0}", fileName);
            } else {
                img = ImageIO.read(s);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading image: {0} - {1}", new Object[]{fileName, e.getMessage()});
        }
        return img;
    }

    public static BufferedImage[] getAllLevels() {
        final File directory = new File("src/main/resources/levels");
        File[] levelFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (levelFiles == null || levelFiles.length == LevelsValues.NO_LEVEL_FILES_LENGHT) {
            return new BufferedImage[LevelsValues.FIRST_LEVEL_POSITION];
        }
        Arrays.sort(levelFiles, Comparator.comparing(File::getName));
        BufferedImage[] images = new BufferedImage[levelFiles.length];
        int index = 0;
        for (File file : levelFiles) {
            try {
                images[index] = ImageIO.read(file);
            } catch (IOException e) {
                images[index] = null;
            }
            index++;
        }
        return images;
    }
}
