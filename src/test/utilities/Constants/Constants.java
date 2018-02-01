package Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Constants{
	
	public static Properties property;
	public static FileInputStream fis;
	
	
	
	public static String getBrowser() {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\resources\\selenium-tests.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property.getProperty("browser");
	
	}
	
	public static String getUrl() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir")+"\\resources\\selenium-tests.properties");
		property = new Properties();
		property.load(fis);
		String browserUrl=property.getProperty("url");
		return browserUrl;
	}
	
}