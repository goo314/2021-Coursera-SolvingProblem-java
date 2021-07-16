
/**
 * check specific substrings in string
 * 
 * @author goo314
 * @version Jul 2, 2021
 */

public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb){
        int firstOccur = stringb.indexOf(stringa);
        if(firstOccur == -1){
            return false;
        }
        
        int secondOccur = stringb.indexOf(stringa, firstOccur+stringa.length());
        if(secondOccur == -1){
            return false;
        }
        
        return true;
    }
    
    public String lastPart(String stringa, String stringb){
        int firstOccur = stringb.indexOf(stringa);
        if(firstOccur == -1){
            return stringb;
        }
        // substring(firstOccur+stringa.length())
        return stringb.substring(firstOccur+stringa.length(), stringb.length());
    }
    
    public void testing(){
        String str1 = "by";
        String str2 = "A story by Abby Long";
        boolean ret = twoOccurrences(str1, str2);
        System.out.println(ret);
        
        str1 = "a";
        str2 = "banana";
        ret = twoOccurrences(str1, str2);
        System.out.println(ret);
        
        str1 = "atg";
        str2 = "ctgtatgta";
        ret = twoOccurrences(str1, str2);
        System.out.println(ret);
        
        String stra = "an";
        String strb = "banana";
        String result = lastPart(stra, strb);
        System.out.println(result);
        
        stra = "zoo";
        strb = "forest";
        result = lastPart(stra, strb);
        System.out.println(result);
    }
    

}
