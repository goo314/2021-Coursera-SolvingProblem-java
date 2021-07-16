/**
 * Write a description of Part1 here.
 * 
 * @author goo314
 * @version Jul 5 , 2021
 */

import edu.duke.*;

public class Part1 {
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
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return"";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        //minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()){
            return"";
        }
        
        /*
        int minIndex = 0;
        if(taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)){
                minIndex = tagIndex;
        }
        
        if(minIndex == -1){
            return"";
        }
        */
       
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void testFindGene(){
        String dna = "AAGTAATGATAG"; // no "ATG"
        System.out.println("DNA " + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene " + gene);
        
        dna = "CATGTAA";
        System.out.println("DNA " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene " + gene);
        
        dna = "CATGTAATAGTGA"; // multiple stopCodon
        System.out.println("DNA " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene " + gene);
        
        dna = "CATGCAA"; // no StopCodon
        System.out.println("DNA " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene " + gene);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while( true ){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while( true ){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public float cgRatio(String dna){
        int entire = dna.length();
        int count = 0;
        int currIndex = 0;
        while( currIndex < dna.length() ){
            if(dna.charAt(currIndex) == 'C' || dna.charAt(currIndex) == 'G'){
                count = count + 1;
            }
            currIndex = currIndex + 1;
        }
        float ratio = (float) count/entire;
        return ratio;
    }
    
    public int countCTG(String dna){
        int count = 0;
        
        int ctgIndex = dna.indexOf("CTG");
        while ( true ){
            if(ctgIndex == -1){
                break;
            }
            count = count + 1;
            ctgIndex = dna.indexOf("CTG", ctgIndex+3);
        }
        
        return count;
    }
    
    public void processGenes(StorageResource sr){
        int countLonger = 0;
        int countHigher = 0;
        int maxLength = 0;
        for(String s: sr.data()){
            if(s.length() > 60){
                System.out.println("String longer " + s);
                countLonger = countLonger + 1;
            }
            if(cgRatio(s) > 0.35){
                System.out.println("String cgRatio higher " + s);
                countHigher = countHigher + 1;
            }
            if(maxLength < s.length()){
                maxLength = s.length();
            }
        }
        System.out.println("num of String longer " + countLonger);
        System.out.println("num of String cgRatio higher " + countHigher);
        System.out.println("longest " + maxLength);
    }
    
    public void testProcessGenes(){
        /*
        String dna = "ATGxxxyyyzzzTAG"; // has gene longer than 9
        System.out.println("DNA " + dna);
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
        
        dna = "ATGxxxTAAATGTAG"; // no genes longer than 9
        System.out.println("DNA " + dna);
        geneList = getAllGenes(dna);
        processGenes(geneList);
        
        dna = "ATGCCCGGGTAA"; // has gene with high cgRatio
        System.out.println("DNA " + dna);
        geneList = getAllGenes(dna);
        processGenes(geneList);
        
        dna = "ATGTAA"; // no gene with high cgRatio
        System.out.println("DNA " + dna);
        geneList = getAllGenes(dna);
        processGenes(geneList);
        */
       
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource gene = getAllGenes(dna);
        for(String g : gene.data()){
            System.out.println(g);
        }
        System.out.println();
        System.out.println();
        System.out.println("total num of gene " + gene.size());
        System.out.println("countCTG " + countCTG(dna));
        processGenes(gene);
    }
}
