
/**
 * Write a description of ImageSaver here.
 * 
 * @author goo314
 * @version Jul 16 , 2021
 */

import edu.duke.*;
import java.io.File;

public class ImageSaver {
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "copy-" + fname;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }

}
