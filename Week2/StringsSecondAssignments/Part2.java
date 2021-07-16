
/**
 * Write a description of Part2 here.
 * 
 * @author goo314
 * @version Jul 3, 2021
 */


public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int currIndex = stringb.indexOf(stringa);
        while ( currIndex != -1 ) {
            count = count + 1;
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
        }
        
        return count;
    }
    
    public void testHowMany(){
        String str1 = "GAA";
        String str2 = "ATGAACGAATTGAATC";
        int ret = howMany(str1, str2);
        System.out.println(ret);
        
        str1 = "AA";
        str2 = "ATAAAA";
        ret = howMany(str1, str2);
        System.out.println(ret);
    }
}
