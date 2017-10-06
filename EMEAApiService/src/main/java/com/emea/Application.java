package com.emea;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.emea.util.ApplicationContextUtil;

/**
 * This is main class to initiate application loading.
 * @author hmolla
 * 
 *
 */
@Configuration
@SpringBootApplication
@ComponentScan
public class Application {
    private static final Logger LOG = Logger.getLogger(Application.class);

    /**
     * Main method to start application
     * @param args
     */
    public static void main(String[] args) {
        LOG.info("application started");
        ConfigurableApplicationContext configurableApplicationContext = null;
        try {
            ApplicationContextUtil.init();
            configurableApplicationContext = SpringApplication.run(
                    Application.class, args);
        } catch (Exception e) {
            LOG.error("Error occurred during startup ", e);
        }
        


    }
}
