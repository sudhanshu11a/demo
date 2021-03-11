/**
 * 
 */
package org.sudhanshu.demo.gateway;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

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
