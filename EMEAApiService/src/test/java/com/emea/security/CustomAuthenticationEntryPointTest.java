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
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emea.controller.InitDb;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:conf/test-app-config.xml" })

public class CustomAuthenticationEntryPointTest extends InitDb{
    
    
    @PersistenceContext
    private EntityManager entityManager;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);
    }
    
    
    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    AuthenticationException ae;
    
    @Test
    @Transactional(Transactional.TxType. REQUIRES_NEW)
    public void testOnLogoutSuccess(){
        try{
            
            customAuthenticationEntryPoint.commence(request, response, ae);
            
        
        } catch(Exception e){
            
        }
    }
}
