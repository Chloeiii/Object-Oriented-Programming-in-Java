public class Part2{
	public String findSimpleGene(String dna){
		String result = "";
		String testDNA = dna.toUpperCase();
		int startCodon = testDNA.indexOf("ATG");
		if (startCodon == -1){
			return "";
		} 
		int stopCodon = testDNA.indexOf("TAA", startCodon + 3);
		if (stopCodon == -1){
			return "";
		}
		
		if((stopCodon - startCodon) % 3 == 0){
			return dna.substring(startCodon, stopCodon+3);
		}

		return result;
	}

	public void testSimpleGene(){
		String case1 = "ATGGGTTAAGTC";
		System.out.println("case1");
		System.out.println(case1);
		System.out.println(findSimpleGene(case1));

    	String case2 = "gatgctataat";
    	System.out.println("case2");
    	System.out.println(case2);
    	System.out.println(findSimpleGene(case2));
		
	}
}