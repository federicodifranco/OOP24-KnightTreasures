package it.unibo.knightreasures.utilities;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

public class ResourceFuncUtilities {

    private static final Logger LOGGER = Logger.getLogger(ResourceFuncUtilities.class.getName());

    private ResourceFuncUtilities() {
    }

    public static Image getSources(final String fileName) {
        return new ImageIcon("src/main/resources/" + fileName + ".png").getImage();
    }

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

    public static BufferedImage loadSources(final String fileName) {
        BufferedImage img = null;
        try (InputStream s = ResourceFuncUtilities.class.getResourceAsStream("/" + fileName + ".png")) {
            if (s == null) {
                Logger.getLogger(ResourceFuncUtilities.class.getName()).log(java.util.logging.Level.SEVERE, "File not found: ", fileName);
            } else {
                img = ImageIO.read(s);
            }
        } catch (IOException e) {
            Logger.getLogger(ResourceFuncUtilities.class.getName()).log(java.util.logging.Level.SEVERE, "Error reading image: " + fileName, e);
        }
        return img;
    }

    public static int[][] createLevel() {
        int[][] level = new int[Window.TILES_IN_HEIGHT][Window.TILES_IN_WIDTH];
        BufferedImage img = ResourceFuncUtilities.loadSources("level_one_data");

        for (int j = 0; j < Window.TILES_IN_HEIGHT && j < img.getHeight(); j++) {
            for (int i = 0; i < Window.TILES_IN_WIDTH && i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= LevelsValues.LEVEL) {
                    value = 0;
                }
                level[j][i] = value;
            }
        }
        return level;
    }

}
