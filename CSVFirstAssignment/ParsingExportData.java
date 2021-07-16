
/**
 * Write a description of ParsingExportData here.
 * 
 * @author goo314
 * @version Jul 11 , 2021
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        /*
        String InfoOfCountry = countryInfo(parser, "Nauru");
        System.out.println(InfoOfCountry);
        */
       /*
        parser = fr.getCSVParser();
        int numOfExporters = numberOfExporters(parser, "cocoa");
        System.out.println("\nnumberOfExporters : " + numOfExporters);
        */
        
        parser = fr.getCSVParser();
        System.out.print("\n");
        bigExporters(parser, "$999,999,999,999");
        
        /*
        parser = fr.getCSVParser();
        System.out.println("\nlistExporters : ");
        listExportersTwoProducts(parser, "cotton", "flowers");
        */
    }
    
    public String countryInfo(CSVParser parser, String country){
        String result = "";
        for(CSVRecord record : parser){
            String whichCountry = record.get("Country");
            if(whichCountry.equals(country)){
                result += whichCountry;
                result += ": ";
                result += record.get("Exports");
                result += ": ";
                result += record.get("Value (dollars)");
                return result;
            }
        }
        
        result = "NOT FOUND";
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int num = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                num = num + 1;
            }
        }
        return num;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                System.out.print(record.get("Country") + " ");
                System.out.println(value);
            }
        }
    }
}
