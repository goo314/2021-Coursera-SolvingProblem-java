
/**
 * Write a description of BatchGrayscaleAndImageInversion here.
 * 
 * @author goo314
 * @version Jul 16 , 2021
 */

import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed()+inPixel.getBlue()+inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource convertedImage = makeGray(image);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            convertedImage.setFileName(f.getParentFile() + "/" + newName);
            convertedImage.save();
        }
    }

}
