package com.emea.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.ResourceUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ResourceUtils.class})
public class CommonUtilPropertyTest {
    private static final String PATH_APP_PROPERTIES = "conf/application.properties";
    @Test
    public void testLoadApplicationPropertiesWithException()
            throws FileNotFoundException {
        PowerMockito.mockStatic(ResourceUtils.class);
        Mockito.when(ResourceUtils.getFile(PATH_APP_PROPERTIES)).thenThrow(
                FileNotFoundException.class);
        CommonUtil.loadApplicationProperties();
    }

    @Test
    public void testLoadApplicationPropertiesWithIOException()
            throws IOException {
        PowerMockito.mockStatic(ResourceUtils.class);
        Mockito.when(ResourceUtils.getFile(PATH_APP_PROPERTIES)).thenThrow(
                IOException.class);
        CommonUtil.loadApplicationProperties();
    }
}
