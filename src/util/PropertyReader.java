package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private String configFilePath = "config.properties";
	private static Properties properties = new Properties();
	
	public PropertyReader() {
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath);
	
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				System.err.println("Could not open config file");
				return;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getString(String propName) {
		return properties.getProperty(propName);
	}
}
