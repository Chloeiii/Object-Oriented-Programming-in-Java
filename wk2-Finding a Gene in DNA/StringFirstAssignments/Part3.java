public class Part3{
	public boolean twoOccurrences(String stringa, String stringb){
		int firstOccurIndex = stringb.indexOf(stringa);
		if (firstOccurIndex >= 0){
			int secondStartIndex = firstOccurIndex + stringa.length();
			if(stringb.indexOf(stringa, secondStartIndex) >= 0){
				return true;
			}
			
		}
		return false;
	}	

	public String lastPart(String stringa, String stringb){
		int startIndex = stringb.indexOf(stringa);
		if (startIndex >= 0){
			return stringb.substring(startIndex + stringa.length());
		}
		return stringb;
	}

	public void testing(){
		System.out.println(twoOccurrences("l", "llreufe"));//true
		System.out.println(twoOccurrences("l", "reufell"));//true
		System.out.println(twoOccurrences("e", "llrufe"));//false
		System.out.println(twoOccurrences("lll", "llreufe"));//false
		System.out.println(lastPart("ba", "banana"));
		System.out.println(lastPart("zoo", "forest"));
	}
}