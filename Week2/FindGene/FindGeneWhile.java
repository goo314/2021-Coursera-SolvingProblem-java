/**
 * Find gene with length which is multiple of 3 in given DNA
 * 
 * @author goo314
 * @version (Jul 2, 2021
 */

import edu.duke.*;

public class FindGeneWhile {
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex+3);
        while(currIndex != -1){
            if((currIndex-startIndex)%3 == 0){
                return dna.substring(startIndex, currIndex+3);
            }
            else{
                currIndex = dna.indexOf("TAA", currIndex+1);
            }
        }
        return "";        
    }
    
    public void testFindGene(){
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene is " + gene);
    }
}
