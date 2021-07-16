
/**
 * Find a gene with start codon 'ATG', end codon 'TAA' and length multiple of 3 in given DNA
 * 
 * @author goo314
 * @version Jun 30, 2021
 */

public class Part1 {
    
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if(stopIndex == -1){
            return"";
        }
        
        if((stopIndex-startIndex)%3 == 0){
            return dna.substring(startIndex, stopIndex+3);
        }
        
        return "";
    }
    
    public void testSimpleGene(){
        String dna = "AAAAAAAAAAAATAAC"; // no "ATG"
        System.out.println("DNA " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "ACTATGCTA"; // no "TAA"
        System.out.println("DNA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "AAAAAAAAAAAA"; // no "ATG"or no "TAA"
        System.out.println("DNA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "ACTATGCACTAACG"; // with ATG, TAA and multiple of 3
        System.out.println("DNA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene " + gene);
        
        dna = "ACTATGACTAACG"; // with ATG, TAA and not multiple of 3
        System.out.println("DNA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene " + gene);
        
    }
}
