package com.cal.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
public class ResourceUtil {
 
    public static String getMessage(String key) {
 
    	Properties prop = new Properties();
		InputStream input = null;
		String keyresult = null;

		try {
			prop.load(ResourceUtil.class.getClassLoader().getResourceAsStream("/currency.properties"));
			//input = new FileInputStream("currency.properties");

			// load a properties file
			//prop.load(input);
			keyresult = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return keyresult;
	  }
 
}