package com.cal.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test2 {

	public static void main(String[] args) {

			Properties prop = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream("currency.properties");

				// load a properties file
				prop.load(input);

				// get the property value and print it out
				System.out.println(prop.getProperty("AUDUSD"));
				System.out.println(prop.getProperty("CADUSD"));
				System.out.println(prop.getProperty("USDCNY"));

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

		  }

}
