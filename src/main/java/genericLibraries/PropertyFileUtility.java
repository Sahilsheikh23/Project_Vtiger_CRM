package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {

	private Properties property;
	
	public void propertyFileInitialization(String propertyPath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(propertyPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDataFromPropertyFile(String key) {
		String data = property.getProperty(key);
		return data;
	}
	
	
}
