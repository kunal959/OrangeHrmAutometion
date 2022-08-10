package com.OrangeHRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {
	Properties prop;
	public PropertyHandling(String filePath) throws IOException  
	{
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(filePath);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getProperty(String key)
	{
		return prop.getProperty(key);
	}

}
