import edu.duke.*;
/**
 * YA
 * @2020-11-01
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
    
    public float cgRatio(String dna){
        int numCG = 0;
        int index = 0;
        while (index < dna.length()){
            if ((dna.charAt(index) == 'C') || (dna.charAt(index) == 'G')){
                numCG += 1;
            }           
            index += 1;       
        }   
        float numCGf = (float)numCG;
        return (numCGf/dna.length()); 
    }
    
    public void processGenes(StorageResource sr){
        //print all the Strings in sr that are longer than 9 characters
        //print the number of Strings in sr that are longer than 9 characters
        //print the Strings in sr whose C-G-ratio is higher than 0.35
        
        int count60 =  0;
        System.out.println("trings in sr that are longer than 60 characters");
        for (String s : sr.data()){
            if(s.length() > 60){
                count60 += 1;
                //System.out.println(s);
            }
        }
        System.out.println("the number of Strings in sr that are longer than 60 characters");
        System.out.println(count60);
        
        int countCGratio = 0;
        int longestLenth = 0;
        System.out.println("trings in sr whose C-G-ratio is higher than 0.35");
        for (String s : sr.data()){   
            if(s.length() > longestLenth){
                longestLenth = s.length();
            }
            
            if(cgRatio(s) > 0.35){
                countCGratio += 1;
                System.out.println(s);
            }
        }
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("the number of strings in sr whose C-G-ratio is higher than 0.35");
        System.out.println(countCGratio);        
        
        //print the length of the longest gene in sr
        System.out.println("the length of the longest gene in sr");
        System.out.println(longestLenth);  
        
        System.out.println("the number of genes");
        System.out.println(sr.size());
    }

    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();    
        dna = dna.toUpperCase();
        //int countCTG = 0;
        //int startIndex = 0;
        //int curCTG = findStopCodon(dna, startIndex, "CTG");
        //while(curCTG < dna.length()){
        //    countCTG += 1;
        //    curCTG = findStopCodon(dna, curCTG + 3, "CTG");   
        //}
        //System.out.println(countCTG);
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        
        
    }
}
