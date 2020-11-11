import edu.duke.*;
/**
 * @YA
 * @2020-11-01
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        //This method returns the index of the first occurrence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex. 
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        //If there is no such stopCodon, this method returns the length of the dna strand.
        if (stopIndex == -1 ){
            return dna.length();
        }
        
        while((stopIndex-startIndex)%3 != 0){
            
            //If there is no such stopCodon, this method returns the length of the dna strand.
            if (stopIndex == -1){
                return dna.length();
            }
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        return stopIndex;
    }
    
    public void testFindStopCodon (){
        //               ATGv  v  v  v  TAGv  TAG
        String dna = "HGIATGFFFFTAAFFFFFTAGFEITAGFEKH";
        int result = findStopCodon(dna, 3, "TAG");
        System.out.println("should be 18");
        System.out.println(result);
        
        dna = "HGIATGFFFFTAAFFFFFTAAFEITAAFEKH";
        result = findStopCodon(dna, 3, "TAG");
        System.out.println("should be 31");
        System.out.println(result);
    }
    
    public String findGene(String dna){
        //Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
        int firstATG = dna.indexOf("ATG");
        if (firstATG == -1){
            return "";
        }
        //Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
        int firstTAA = findStopCodon(dna, firstATG, "TAA");
        //Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
        int firstTAG = findStopCodon(dna, firstATG, "TAG");
        //Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
        int firstTGA = findStopCodon(dna, firstATG, "TGA");
        //Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.
        int minIndex = firstTAA;
        if(firstTAG < firstTAA){
            minIndex = firstTAG;
        }
        if(firstTGA < minIndex){
            minIndex = firstTGA;
        }
        
        if (minIndex < dna.length()){
            return dna.substring(firstATG, minIndex+3);
        }else{
            return "";
        }
         
    }
    
    public void testFindGene(){
        //DNA with no “ATG”
        String dna = "HGIATTFFFFTAAFFFFFTAGFEITAGFEKH";
        System.out.println("CASE 1");
        System.out.println(dna);
        System.out.println("should be empty");
        System.out.println(findGene(dna));
        //DNA with “ATG” and one valid stop codon
        dna = "HGIATGTFFFFTAAFTAAUIREUREU";
        System.out.println("CASE 2");
        System.out.println(dna);
        System.out.println("should be ATGTFFFFTAAFTAA");
        System.out.println(findGene(dna));        
        //DNA with “ATG” and multiple valid stop codons
        dna = "HGIATGTFFFFTAAFTAATAGTGATEITE";
        System.out.println("CASE 3");
        System.out.println(dna);
        System.out.println("should be ATGTFFFFTAAFTAA");
        System.out.println(findGene(dna));      
        //DNA with “ATG” and no valid stop codons
        dna = "HGIATGTFFFFTAAFRELIROEIUREEITE";
        System.out.println("CASE 4");
        System.out.println(dna);
        System.out.println("should be empty");
        System.out.println(findGene(dna));    
        
    }
    
    public void printAllGenes (String dna){
        String curDNA = findGene(dna);
        while(curDNA != ""){
            System.out.println(curDNA);
            dna = dna.substring(dna.indexOf(curDNA) + curDNA.length());
            curDNA = findGene(dna);
        }
    }
   
    
    public void testprintAllGenes (){  
        System.out.println("should be ATGWURYROTAA, ATGOUITAG, ATGIEHORJSKETGA");
        printAllGenes("RTEUREATGWURYROTAAEATGOUITAGATGIEHORJSKETGAJEKW");
        
    }
    
    public StorageResource getAllGenes (String dna){
        StorageResource dnaList = new StorageResource();
        String curDNA = findGene(dna);
        while(curDNA != ""){
            //System.out.println(curDNA);
            dnaList.add(curDNA);
            dna = dna.substring(dna.indexOf(curDNA) + curDNA.length());
            curDNA = findGene(dna);
        }
        return dnaList;
    }
    
    public void testgetAllGenes (){  
        System.out.println("should be ATGWURYROTAA, ATGOUITAG, ATGIEHORJSKETGA");
        StorageResource dnaList = getAllGenes("RTEUREATGWURYROTAAEATGOUITAGATGIEHORJSKETGAJEKW");
        for (String eachDNA : dnaList.data()){
            System.out.println(eachDNA);
        }
        
    }
    
}


