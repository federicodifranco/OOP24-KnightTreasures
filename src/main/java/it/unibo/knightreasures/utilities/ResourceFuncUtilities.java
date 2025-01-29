package it.unibo.knightreasures.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResourceFuncUtilities {

    private static final Logger LOGGER = Logger.getLogger(ResourceFuncUtilities.class.getName());

    private ResourceFuncUtilities(){}

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
        try(InputStream s = ResourceFuncUtilities.class.getResourceAsStream("/" + fileName + ".png")){
            if( s == null){
                Logger.getLogger(ResourceFuncUtilities.class.getName()).log(java.util.logging.Level.SEVERE,"File not found: " + fileName);
            } else {
                img = ImageIO.read(s);
            }
        } catch (IOException e) {
            Logger.getLogger(ResourceFuncUtilities.class.getName()).log(java.util.logging.Level.SEVERE, "Error reading image: " + fileName, e);

        }
        return img;
    }

}
