package com.emea.util;

import java.io.File;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Util class to initiate application.
 * 
 * @author hmolla
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static final String LOG_FILE_PATH = "conf/log4j.properties";
    private static final String APP_CONTEXT_PATH_STR = "app.context.file.path";
    private static final String APP_CONTEXT_PATH_VALUE = "classpath:conf/app-config.xml";
    private static Logger LOG = Logger.getLogger(ApplicationContextUtil.class);
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * Method to initiate application.
     * 
     * @throws SQLException
     */
    public static void init() throws SQLException {

        CommonUtil.initSpringProfile(CommonUtil.loadApplicationProperties());
        PropertyConfigurator.configure(new File(LOG_FILE_PATH)
                .getAbsolutePath());

        String contextFilePath = System.getProperty(APP_CONTEXT_PATH_STR,
                APP_CONTEXT_PATH_VALUE);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                contextFilePath);
        applicationContext.registerShutdownHook();
    }
}
