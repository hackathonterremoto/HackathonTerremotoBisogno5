package org.hackathon.soluzione5.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProperties {

	protected static ConfigurationProperties singleton;
	private  Properties properties;
	
	public static ConfigurationProperties getInstance()
	{
		if(singleton == null){
			try {
				singleton = new ConfigurationProperties();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return singleton;
	}
	
	protected ConfigurationProperties() throws IOException
	{

        InputStream is = ConfigurationProperties.class.getResourceAsStream( "/environment.properties" );
        properties = new Properties();
        properties.load( is );
	}
	
	
	
	public String getProperty(String propertyName)
	{
		return properties.getProperty(propertyName);
	}
	
}