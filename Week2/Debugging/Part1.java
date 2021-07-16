
/**
 * Write a description of Part1 here.
 * 
 * @author goo314
 * @version Jul 4, 2021
 */

public class Part1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
               break;
            }
            /*
            System.out.println("index ");
            System.out.println(index+1);
            System.out.println(index+4);
            */
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    public void test() {
        //no code yet
        //findAbc("abcd");
        //findAbc("abcdabc");
        
        //findAbc("yabcyabc");
        //findAbc("woiehabchi");
        //findAbc("aaaaabc");
        //findAbc("abcbbbabcdddabc");
        findAbc("eusabce");
     }
}
