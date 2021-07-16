/**
 * Write a description of Part3 here.
 * 
 * @author goo314
 * @version Jul 4, 2021
 */

import edu.duke.*;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon(){
        //            01234567890123456789012345
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        int dex = findStopCodon(dna, 0, "TAA");
        System.out.println(dex);
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return"";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, tagIndex);
        minIndex = Math.min(minIndex, tgaIndex);
        minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()){
            return"";
        }
       
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void testFindGene(){
        String dna = "AAGTAATGATAG"; // no "ATG"
        System.out.println("DNA " + dna);
        String gene = findGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "CATGTAA";
        System.out.println("DNA " + dna);
        gene = findGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "CATGTAATAGTGA"; // multiple stopCodon
        System.out.println("DNA " + dna);
        gene = findGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "CATGCAA"; // no StopCodon
        System.out.println("DNA " + dna);
        gene = findGene(dna);
        System.out.println("Gene " + gene);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while( true ){
            dna = dna.substring(startIndex);
            String currentGene = findGene(dna);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public int countGenes(String dna){
        int count = 0;
        int startIndex = 0;
        while( true ){
            dna = dna.substring(startIndex);
            String currentGene = findGene(dna);
            if(currentGene.isEmpty()){
                break;
            }
            count = count + 1;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;
    }
    
    public void testCountGene(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("dna " + dna);
        int result = countGenes(dna);
        System.out.println("how many " + result);
    }
}
