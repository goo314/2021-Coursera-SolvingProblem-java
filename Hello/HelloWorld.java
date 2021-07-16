/**
 * Print given text file
 * 
 * @author goo314
 * @version Jun 21, 2021
 */

import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}
