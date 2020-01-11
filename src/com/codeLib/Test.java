package com.codeLib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {
	public static void main(String[] args) {
		Properties properties = new Properties();
		InputStream is = C3p0_DataSource.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(properties.getProperty("user"));
	}
}
