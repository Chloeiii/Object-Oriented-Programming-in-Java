
/**
 * Count how many genes are in a strand of DNA
 * 
 * @YA
 * @2020-10
 */
public class Part3 {
    
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
    
    public void printAllGenes (String dna){
        String curDNA = findGene(dna);
        while(curDNA != ""){
            System.out.println(curDNA);
            dna = dna.substring(dna.indexOf(curDNA) + curDNA.length());
            curDNA = findGene(dna);
        }
    }
    
    public int countGenes(String dna){
        int count = 0;
        if (dna.length() < 3){
            return 0;
        }
        String curDNA = findGene(dna);
        while(curDNA!=""){
            count += 1;
            dna = dna.substring(dna.indexOf(curDNA) + curDNA.length());
            curDNA = findGene(dna);
        }
        return count;
    }
    
    public void testCountGenes (){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT")); //2
        System.out.println(countGenes("ATGTAAGATGCCCTAGTATGTGA"));//3
        System.out.println(countGenes("ATAAAA")); //0
        System.out.println(countGenes("")); //0

    }
    
}
