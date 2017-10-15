package com.emea;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.emea.controller.AccountInfoController;
import com.emea.controller.InitDb;
import com.emea.dto.AccountInfoVo;
import com.emea.service.AccountInfoService;
import com.emea.util.CommonUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonUtil.class, Integer.class})
@PowerMockIgnore("javax.management.*")
public class ApplicationIT extends InitDb {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private WebApplicationContext context;
    @Mock
    private AccountInfoService accountInfoService;

    @InjectMocks
    private AccountInfoController accountInfoController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CommonUtil.class);
        PowerMockito.mockStatic(Integer.class);

        mockMvc = MockMvcBuilders.standaloneSetup(accountInfoController)
                .build();

    }

    @Test
    public void testEMEAApiService() throws Exception {
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        Mockito.when(accountInfoService.getAccountDetails(anyLong(), anyLong()))
                .thenReturn(accountInfoVo);

        Integer data = 1;
        Mockito.when(Integer.valueOf(anyString())).thenReturn(data);
        Mockito.when(Integer.valueOf(null)).thenReturn(data);
        Mockito.when(CommonUtil.isNumericRegex(anyString())).thenReturn(true);
        Mockito.when(CommonUtil.getThirdPartyResponse(anyString(), anyInt()))
                .thenReturn("");

        Whitebox.setInternalState(accountInfoController, "connectionTimeOut",
                "5");

        mockMvc.perform(
                post(
                        "/emeaapiservice/accountdetails?access_token=f6403410-6330-41a7-b384-b86952292bfc1")
                        .accept((MediaType.APPLICATION_JSON))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{ \"accountNumber\":\"1\", \"sortCode\":\"122312\" }"))
                .andExpect(status().isOk())

        ;

    }
    
    @After
    public void tearDown() {
        shutDownDB();

    }

}
