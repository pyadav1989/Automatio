/**
 * 
 */
package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author prashant
 *
 */
public class CommonUtilClass {

	public static Properties getPropFile(String projPath) {
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(projPath+File.separator+"config.properties"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
