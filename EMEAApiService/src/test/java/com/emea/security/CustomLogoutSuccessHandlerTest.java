package com.emea.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.emea.controller.InitDb;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf/test-app-config.xml"})
@WebAppConfiguration
public class CustomLogoutSuccessHandlerTest extends InitDb {
    private static final String HEADER_AUTHORIZATION = "authorization";
    private static final String BEARER_AUTHENTICATION = "Bearer ";
    @PersistenceContext
    private EntityManager entityManager;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);
        CommonUtility.setUpUser(entityManager);
    }
    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    Authentication authentication;

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testOnLogoutSuccess() {
        try {

            Mockito.when(request.getHeader(HEADER_AUTHORIZATION)).thenReturn(
                    BEARER_AUTHENTICATION);

            customLogoutSuccessHandler.onLogoutSuccess(request, response,
                    authentication);
        } catch (Exception e) {

        }
    }
}
