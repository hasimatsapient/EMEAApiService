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
import com.emea.exception.ApplicationException;
import com.emea.util.CommonUtil;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf/test-app-config.xml"})
public class AccountInfoServiceIT extends InitDb {

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
    public void testCreateOrUpdateAccountDetails() throws ApplicationException {
        AccountInfoVo accountInfoVoINput = new AccountInfoVo();
        accountInfoVoINput.setAccountNumber(null);
        accountInfoVoINput.setSortCode(200013L);
        
    //  CommonUtility.prepareDataWithSql(entityManager, "insert into SORT_CODE values (200013)", false);
        AccountInfoVo accountInfoVo = accountInfoService.createOrUpdateAccountDetails(accountInfoVoINput);

        assertNotNull(accountInfoVo);
    }
   @Test(expected=ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateOrUpdateAccountDetailsInvalidData() throws ApplicationException {
        AccountInfoVo accountInfoVoINput = new AccountInfoVo();
        accountInfoVoINput.setAccountNumber(3L);
        accountInfoVoINput.setSortCode(200013L);
        
    //  CommonUtility.prepareDataWithSql(entityManager, "insert into SORT_CODE values (200013)", false);
        AccountInfoVo accountInfoVo = accountInfoService.createOrUpdateAccountDetails(accountInfoVoINput);

       // assertNotNull(accountInfoVo);
    }
    
    @Test(expected=ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateOrUpdateAccountDetailsWithNullSortCode() throws ApplicationException {
        AccountInfoVo accountInfoVoINput = new AccountInfoVo();
        accountInfoVoINput.setAccountNumber(3L);
        accountInfoVoINput.setSortCode(null);
        AccountInfoVo accountInfoVo = accountInfoService.createOrUpdateAccountDetails(accountInfoVoINput);

        assertNotNull(accountInfoVo);
        
        
    }
    
    @Test(expected=ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateOrUpdateAccountDetailsWithException() throws ApplicationException {
        AccountInfoVo accountInfoVoINput = new AccountInfoVo();
        accountInfoVoINput.setAccountNumber(null);
        accountInfoVoINput.setSortCode(null);
        AccountInfoVo accountInfoVo = accountInfoService.createOrUpdateAccountDetails(accountInfoVoINput);

        assertNotNull(accountInfoVo);
        
        
    }
    
    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateOrUpdateAccountDetailsWithUpdate() throws ApplicationException {
        AccountInfoVo accountInfoVoINput = new AccountInfoVo();
        accountInfoVoINput.setAccountNumber(null);
        accountInfoVoINput.setSortCode(200013L);
        AccountInfoVo accountInfoVo = accountInfoService.createOrUpdateAccountDetails(accountInfoVoINput);

        
        AccountInfoVo accountInfoVoINput1 = new AccountInfoVo();
        accountInfoVoINput1.setAccountNumber(accountInfoVo.getAccountNumber());
        accountInfoVoINput1.setSortCode(200013L);
        AccountInfoVo accountInfoVo1 = accountInfoService.createOrUpdateAccountDetails(accountInfoVoINput1);

        
        
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
