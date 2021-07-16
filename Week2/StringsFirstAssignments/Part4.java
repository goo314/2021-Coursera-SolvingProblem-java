/**
 * Find youtube links in URL
 * 
 * @author goo314
 * @version Jul 2, 2021
 */

import edu.duke.*;
public class Part4 {
    
    public void findWebLinks(){
        URLResource file = new  URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
   	for (String item : file.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
       	       int beg = item.lastIndexOf("\"", pos);
       	       int end = item.indexOf("\"", pos+1);
       	       System.out.println(item.substring(beg+1, end));
               }
   	}
    }
}
