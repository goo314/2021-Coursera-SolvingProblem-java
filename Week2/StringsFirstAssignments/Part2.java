
/**
 * Find a gene with given start codon and end codon in given DNA
 * 
 * @author goo314
 * @version Jun 30, 2021
 */

public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
            return "";
        }
        
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if(stopIndex == -1){
            return"";
        }
        
        if((stopIndex-startIndex)%3 == 0){
            return dna.substring(startIndex, stopIndex+3);
        }
        
        return "";
    }
    
    public void testSimpleGene(){
        String dna = "ATGGGTTAAGTC";
        System.out.println("DNA " + dna);
        String gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene " + gene);
        
        dna = "gatgctataa";
        System.out.println("DNA " + dna);
        gene = findSimpleGene(dna, "atg", "taa");
        System.out.println("Gene " + gene);
    }
}
