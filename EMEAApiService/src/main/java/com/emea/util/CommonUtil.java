package com.emea.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

public class CommonUtil {
	private static final String ERROR_OCCURED = "Error occurred";
    private static final String PATH_APP_PROPERTIES = "conf/application.properties";
    private static final String SERVER = "server";
    private static final String SPRING = "spring.";
    private static Logger LOG =   Logger.getLogger(CommonUtil.class);
	
    public static Properties loadApplicationProperties() {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(new FileSystemResource(ResourceUtils.getFile(PATH_APP_PROPERTIES)));
        } catch (final FileNotFoundException e) {
            LOG.info(ERROR_OCCURED, e);
        } catch (final IOException e) {
            LOG.info(ERROR_OCCURED, e);
        }
        return properties;

    }
    
    /**
     * Read SQL from file and convert to String.
     * @param resource
     * @return
     */
    public static String readResourceAsString(String resource) {
        try (InputStream is = new ClassPathResource(resource).getInputStream()) {
            return IOUtils.toString(is);
        } catch (IOException e) {
        	LOG.error(e);
           e.printStackTrace();
           return null;
        }
    }
    
    public static void initSpringProfile(Properties properties) {
       
        for (String propertyName : properties.stringPropertyNames()) {
            if (propertyName.startsWith(SERVER) || propertyName.startsWith(SPRING)) {
                String propertyValue = properties.getProperty(propertyName);
                System.setProperty(propertyName, propertyValue);
            }
        }

    }
   
}
