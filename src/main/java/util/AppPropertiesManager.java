package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppPropertiesManager {

    private static final Logger LOGGER = Logger.getLogger(AppPropertiesManager.class);

    private static final String appPropertiesFileName = "application.properties";
    private Properties appProperties;

    public AppPropertiesManager() {
        appProperties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(appPropertiesFileName);
        try {
            appProperties.load(stream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getProperty(String propertyName) {
        return appProperties.getProperty(propertyName);
    }
}
