package com.emea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import com.emea.controller.AccountInfoController;
import com.emea.controller.InitDb;
import com.emea.util.CommonUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonUtil.class})
@PowerMockIgnore("javax.management.*")
public class ApplicationTest extends InitDb {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String APP_CONTEXT_PATH_STR = "app.context.file.path";
    private static final String CONFIG_PATH = "classpath:conf/test-app-config.xml";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AccountInfoController accountInfoController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CommonUtil.class);

    }

    @After
    public void tearDown() {
        shutDownDB();

    }

    public void initSpringProfile(Properties properties) {
        if (properties != null) {
            for (String propertyName : properties.stringPropertyNames()) {
                String propertyValue = properties.getProperty(propertyName);
                System.setProperty(propertyName, propertyValue);
            }
        }
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testMain() {

        Properties properties = new Properties();
        System.setProperty(APP_CONTEXT_PATH_STR, CONFIG_PATH);

        try {
            properties = PropertiesLoaderUtils
                    .loadProperties(new FileSystemResource(
                            ResourceUtils
                                    .getFile("classpath:conf/testApplication.properties")));
        } catch (final FileNotFoundException e) {

        } catch (final IOException e) {

        }

        initSpringProfile(properties);

        Mockito.when(CommonUtil.loadApplicationProperties()).thenReturn(
                properties);
        String[] args = {};

        Application.main(args);
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testMainWithError() {
        Application.main(null);
    }

}
