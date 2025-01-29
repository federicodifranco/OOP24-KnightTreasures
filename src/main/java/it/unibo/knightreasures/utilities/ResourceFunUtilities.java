package it.unibo.knightreasures.utilities;

import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.InputStream;

public class ResourceFunUtilities {

    private static final Logger LOGGER = Logger.getLogger(ResourceFunUtilities.class.getName());

    private ResourceFunUtilities(){}

    public static Image getSource(final String fileName){
        return new ImageIcon("src/main/resources/" + fileName + ".png").getImage();
    }

    public static BufferedImage getBufferedSources(final String fileName){
        final File file = new File("src/main/resources/" + fileName + ".png");
        BufferedImage img = null;
        try{
            img = ImageIO.read(file);
        } catch (IOException e){
            LOGGER.severe(e.getMessage());
        }
        return img;
    }

    public static BufferedImage loadSources(final String fileName){
        BufferedImage img = null;
        try(InputStream s = ResourceFunUtilities.class.getResourceAsStream("/" + fileName + ".png")){
            if( s == null){
                Logger.getLogger(ResourceFunUtilities.class.getName()).log(java.util.logging.Level.SEVERE,"File not found: " + fileName);
            } else {
                img = ImageIO.read(s);
            }
        } catch (IOException e) {
            Logger.getLogger(ResourceFunUtilities.class.getName()).log(java.util.logging.Level.SEVERE, "Error reading image: " + fileName, e);

        }
        return img;
    }

}
