
/**
 * This assignment will write a method to determine how many occurrences of a string appear in another string
 * 
 * @YA
 * @2020-10
 */
public class Part2 {
    public int howMany (String stringa, String stringb){
        if((stringa == "") || (stringb == "")){
            return 0;
        }
        int count = 0;
        String curString = stringa;
        while(curString.indexOf(stringb) >= 0){
            //System.out.println(curString);
            //System.out.println(curString.indexOf(stringb));
            count += 1;
            curString = curString.substring(curString.indexOf(stringb) + stringb.length());
        }
        return count;
    
    }
    
    public void testHowMany(){
        System.out.println(howMany("ATGAACGAATTGAATC","GAA")); //3
        System.out.println(howMany("ATAAAA","AA")); //2
        System.out.println(howMany("","AA")); //0
        System.out.println(howMany("aaa","")); //0
        System.out.println(howMany("","")); //0
        System.out.println(howMany("aaa","b")); //0
    
    }
}
