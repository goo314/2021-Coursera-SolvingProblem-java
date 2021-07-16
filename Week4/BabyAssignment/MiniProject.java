/**
 * Write a description of MiniProject here.
 * 
 * @author goo314
 * @version Jul 13 , 2021
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MiniProject {
    public void totalBirths(FileResource fr){
        int totalNames = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            //int numBorn = Integer.parseInt(rec.get(2));
            //totalBirths += numBorn;
            totalNames += 1;
            if(rec.get(1).equals("M")) {
                totalBoys +=  1;
            }
            else{
                totalGirls += 1;
            }
        }
        System.out.println("total names = " + totalNames);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int rank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank += 1;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int count = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                count += 1;
                if(count == rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String nameInYear = getName(newYear, rank, gender);
        
        if(gender.equals("M")){
            System.out.println(name + " born in " + year + " would be " + nameInYear + 
                               " if he was born in " + newYear);
        }
        else {
            System.out.println(name + " born in " + year + " would be " + nameInYear + 
                               " if she was born in " + newYear);
        }
    }
    
    public int yearOfHighestRank(String name, String gender){
        int highestSoFar = -1;
        int yearOfHighest = -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if(currentRank > 0 && highestSoFar == -1){
                highestSoFar = currentRank;
                yearOfHighest = currentYear;
            }
            else{
                if(currentRank > 0 && currentRank < highestSoFar){
                    highestSoFar = currentRank;
                    yearOfHighest = currentYear;
                }
            }
        }
        return yearOfHighest;
    }
    
    public double getAverageRank(String name, String gender){
        double totalRanks = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            count += 1;
            if(currentRank > 0){
                totalRanks += currentRank;
            }
        }
        
        if(totalRanks == 0){
            return -1;
        }
        
        return totalRanks/count;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int totalBirthsHigher = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                if(rec.get(0).equals(name)){
                    return totalBirthsHigher;
                }
                totalBirthsHigher += Integer.parseInt(rec.get(2));
            }
        }
        return -1;
    }
}
