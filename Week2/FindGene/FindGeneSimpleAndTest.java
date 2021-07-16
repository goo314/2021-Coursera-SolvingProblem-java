
/**
 * Find one possible gene in given DNA string
 * 
 * @author goo314
 * @version Jul 2, 2021
 */
public class FindGeneSimpleAndTest {
    
    public String findGeneSimple(String dna){
        // start codon is "ATG"
        // stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
        {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if(stopIndex == -1)
        {
            return "";
        }
        result = dna.substring(startIndex, stopIndex+3);
        
        return result;
    }
    
    public void testFindGeneSimple(){
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is "+ dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is "+ dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("DNA strand is "+ dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "CAGATGAT";
        System.out.println("DNA strand is "+ dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
    }
}
