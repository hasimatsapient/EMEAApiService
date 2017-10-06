package com.emea.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emea.controller.InitDb;
import com.emea.dao.AccountInfoDao;
import com.emea.model.AccountInfoBo;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:conf/test-app-config.xml" })

public class AccountInfoDaoTest extends InitDb{
    
    @Autowired
    private AccountInfoDao accountInfoDao;
    @PersistenceContext
    private EntityManager entityManager;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);
    }
    
    
    @Test
    @Transactional(Transactional.TxType. REQUIRES_NEW)
    public void getAccountInfoByAccountNumberAndSortCodeWithData() {   
        AccountInfoBo accountInfoBo= accountInfoDao.getAccountInfoByAccountNumberAndSortCode(1L, 122312);
        
        assertNotNull(accountInfoBo);
    }
    @Test
    @Transactional(Transactional.TxType. REQUIRES_NEW)
    public void getAccountInfoByAccountNumberAndSortCodeWithNoData() {   
        AccountInfoBo accountInfoBo= accountInfoDao.getAccountInfoByAccountNumberAndSortCode(2L, 122312);
        
        assertNull(accountInfoBo);
    }
    
    
}
