/**
 * 
 */
package org.sudhanshu.demo.eureka;

import java.io.PrintStream;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

/**
 * @author Sudhanshu Sharma
 *
 */
public class CustomBanner implements Banner {
	
	@Override
	public void printBanner(Environment arg0, Class<?> arg1, PrintStream arg2) {
		arg2.println("####################################");
		arg2.println("###### Sud Spring boot banner ######");
		arg2.println("####################################");
	}

}
