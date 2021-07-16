
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author goo314
 * @version Jul 8 , 2021
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest){
        // for each row in the CSV File
        for(CSVRecord record : parser){
            // Look at the "Exports" column
            String export = record.get("Exports");
            // Check if it contains exportOfInterest
            if(export.contains(exportOfInterest)){
                // If so , write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void wholExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}
