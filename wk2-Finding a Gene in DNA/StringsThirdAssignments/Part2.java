
/**
 * 
 * YA
 * 2020-11-01
 */
public class Part2 {
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
    
    public void testcgRatio(){
        System.out.println(cgRatio("ATGCCATAG"));
    }

    public int countCTG(String dna){
        int numCTG = 0;
        String curDNA = dna;
        while (true){
            int CTGindex = curDNA.indexOf("CTG");
            if ((CTGindex >= 0) && (CTGindex%3 == 0)){
                numCTG += 1;
                curDNA = curDNA.substring(CTGindex + 3);
            }else {
                break;
            }
        }   
        return numCTG;
    }
    
    public void testcountCTG(){
        System.out.println(countCTG("ATGCTGTAGCTGJCTG"));
    }
}
