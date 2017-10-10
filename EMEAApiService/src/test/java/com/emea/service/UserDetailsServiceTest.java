package com.emea.service;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emea.controller.InitDb;
import com.emea.exception.UserNotActivatedException;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:conf/test-app-config.xml" })

public class UserDetailsServiceTest extends InitDb{
    
     
    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);
        CommonUtility. setUpUser(entityManager);
      
    }
    
    
    
    @Test
    @Transactional(Transactional.TxType. REQUIRES_NEW)
    public void testGetAccountDetailsWithData() {   
        UserDetails userDetails=  userDetailsService.loadUserByUsername("hasim");
        assertNotNull(userDetails);
    }
    
    @Test(expected=UsernameNotFoundException.class)
    @Transactional(Transactional.TxType. REQUIRES_NEW)
    public void testGetAccountDetailsWithNoData() {   
        UserDetails userDetails=  userDetailsService.loadUserByUsername("abdul");
    }
    
    
    @Test(expected=UserNotActivatedException.class)
    @Transactional(Transactional.TxType. REQUIRES_NEW)
    public void testGetAccountDetailsWithInactiveUserData() { 
        
        Query q  =  entityManager.createNativeQuery("INSERT INTO user_details (username,email, password, activated) VALUES ('mollah', 'hasim@abc.com', 'test', 'false')");
        q.executeUpdate();
        
        q =  entityManager.createNativeQuery("INSERT INTO user_authority (username,authority) VALUES ('mollah', 'ROLE_USER')");
        q.executeUpdate();
        q =  entityManager.createNativeQuery("INSERT INTO user_authority (username,authority) VALUES ('mollah', 'ROLE_ADMIN')");
        
        
        UserDetails userDetails=  userDetailsService.loadUserByUsername("mollah");
    }
    
    
   
}
