
/**
 * Write a description of ImageInversion here.
 * 
 * @author goo314
 * @version Jul 16 , 2021
 */
import edu.duke.*;
import java.io.*;

public class BatchInversion {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setBlue(255-inPixel.getBlue());
            pixel.setGreen(255-inPixel.getGreen());
        }
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource invertedImage = makeInversion(ir);
            String fname = ir.getFileName();
            String newName = "inverted-" + fname;
            invertedImage.setFileName(f.getParentFile() + "/" + newName);
            invertedImage.save();
        }
    }
}
