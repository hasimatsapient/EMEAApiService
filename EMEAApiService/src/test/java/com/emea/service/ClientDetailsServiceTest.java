package com.emea.service;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emea.controller.InitDb;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf/test-app-config.xml"})
public class ClientDetailsServiceTest extends InitDb {
    @Autowired
    private org.springframework.security.oauth2.provider.ClientDetailsService clientDetailsService;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);
        CommonUtility.setUpUser(entityManager);

    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithData() {
        ClientDetails clientDetails = clientDetailsService
                .loadClientByClientId("hasim");

        assertNotNull(clientDetails);
    }

}
