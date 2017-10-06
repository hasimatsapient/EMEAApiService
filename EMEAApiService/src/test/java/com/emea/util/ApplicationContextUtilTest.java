package com.emea.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import com.emea.controller.InitDb;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonUtil.class})
@PowerMockIgnore("javax.management.*")
public class ApplicationContextUtilTest extends InitDb {
    private static final String APP_CONTEXT_PATH_STR = "app.context.file.path";
    private static final String CONFIG_PATH = "classpath:conf/test-app-config.xml";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CommonUtil.class);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInit() {

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
        Mockito.when(CommonUtil.loadApplicationProperties()).thenReturn(
                properties);
        try {
            ApplicationContextUtil.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
