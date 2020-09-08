public class Part1{
	public String findSimpleGene(String dna){
		String result = "";
		//Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
		int startCodon = dna.indexOf("ATG");
		if (startCodon == -1){
			return "";
		} 
		//Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string
		int stopCodon = dna.indexOf("TAA", startCodon + 3);
		if (stopCodon == -1){
			return "";
		}
		//If the length of the substring between the “ATG” and “TAA” is a multiple of 3, then return the substring that starts with that “ATG” and ends with that “TAA”.
		if((stopCodon - startCodon) % 3 == 0){
			return dna.substring(startCodon, stopCodon+3);
		}

		return result;
	}

	public void testSimpleGene(){
		String case1 = "THRJIFDJAETAATLEK";
		System.out.println("case1");
		System.out.println(case1);
		System.out.println(findSimpleGene(case1));

		String case2 = "THRJIATGFDJAETLEK";
		System.out.println("case2");
		System.out.println(case2);
		System.out.println(findSimpleGene(case2));
		

		String case3 = "THRJIDJAETLEK";
		System.out.println("case3");
		System.out.println(case3);
		System.out.println(findSimpleGene(case3));
		

		String case4 = "THRJIATGGLOIJTAATLEK";
		System.out.println("case4");
		System.out.println(case4);
		System.out.println(findSimpleGene(case4));
		

		String case5 = "THRJIATGJGUOEJTAATLEK";
		System.out.println("case5");
		System.out.println(case5);
		System.out.println(findSimpleGene(case5));
		
	}
}