
/**
 * Write a description of Part2 here.
 * 
 * @author goo314
 * @version Jul 4, 2021
 */

public class Part2 {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println("index " + index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+4);
           System.out.println("index after updating " + index);
       }
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       //       01234567890123456789012345678901234567890012
       findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
   }

}
