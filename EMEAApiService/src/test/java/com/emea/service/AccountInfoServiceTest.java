package com.emea.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emea.controller.InitDb;
import com.emea.dto.AccountInfoVo;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf/test-app-config.xml"})
public class AccountInfoServiceTest extends InitDb {

    @Autowired
    private AccountInfoService accountInfoService;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);

    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithData() {
        AccountInfoVo accountInfoVo = accountInfoService.getAccountDetails(1L,
                122312);

        assertNotNull(accountInfoVo);
    }
    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithOutData() {
        AccountInfoVo accountInfoVo = accountInfoService.getAccountDetails(2L,
                122312);

        assertNull(accountInfoVo);
    }

}
