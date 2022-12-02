package reusable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Reusable {
	
	public static String readPropertiesFile(String key)
	{
		
		String txt=null;
		Properties prop = new Properties();
		try {
		    prop.load(new FileInputStream(System.getProperty("user.dir")+"/testdata.properties"));
		    txt=prop.getProperty(key);
		} catch (IOException e) {
		    e.printStackTrace();
	}
		return txt;
	}
}
