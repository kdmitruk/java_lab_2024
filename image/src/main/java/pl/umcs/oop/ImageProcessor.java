package pl.umcs.oop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    private BufferedImage image ;

    public void readImage(String path) throws IOException {
       image = ImageIO.read(new File(path));
    }

    public void writeImage(String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path));
    }

}
