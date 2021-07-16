
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author goo314 
 * @version Jul 11 , 2021
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser){
            if(Double.parseDouble(currentRow.get("TemperatureF")) == -9999){
            }
            else if(coldestSoFar == null){
                coldestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                           " at " + coldest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        File file = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(file == null){
                file = f;
            }
            else{
                CSVRecord lowestRow = coldestHourInFile(new FileResource(file).getCSVParser());
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(lowestRow.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    file = f;
                }
            }
        }
        
        return file.getName();
    }
    
    public void testFileWithColdestTemperature(){
        String fName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fName);
        
        FileResource fr = new FileResource("nc_weather/2013/" + fName);
        CSVRecord lowestRow = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + lowestRow.get("TemperatureF"));
        
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord current : fr.getCSVParser()){
            System.out.println(current.get("DateUTC") + ": " + current.get("TemperatureF"));
        }
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow : parser){
            if(lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                if(!currentRow.get("Humidity").equals("N/A")){
                    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if(currentTemp < lowestTemp){
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                           " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if(lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                if(!currentRow.get("Humidity").equals("N/A")){
                    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if(currentTemp < lowestTemp){
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                           " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for(CSVRecord current : parser){
            sum += Double.parseDouble(current.get("TemperatureF"));
            count += 1;
        }
        return sum/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        int count = 0;
        for(CSVRecord current : parser){
            int currentHumidity = Integer.parseInt(current.get("Humidity"));
            if(currentHumidity >= value){
                sum += Double.parseDouble(current.get("TemperatureF"));
                count += 1;
            }
        }
        
        if(count == 0){
            return -1;
        }
        
        return sum/count;
    }
    
    public void testAverageTemperatueWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(average == -1){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + average);
        }
    }
}
