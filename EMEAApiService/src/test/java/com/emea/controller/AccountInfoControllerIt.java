package com.emea.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.emea.dto.AccountInfoVo;
import com.emea.dto.TransactionVo;
import com.emea.exception.ApplicationException;
import com.emea.util.CommonUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf/test-app-config.xml"})
public class AccountInfoControllerIt extends InitDb {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AccountInfoController accountInfoController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CommonUtility.prepareData(entityManager);

    }

    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithNoData() throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        map.put("sortCode", "122312");
        accountInfoController.getAccountDetails(map);

    }
    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithNoAccountNumber()
            throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("sortCode", "122312");
        accountInfoController.getAccountDetails(map);

    }
    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithNoSortCode()
            throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        accountInfoController.getAccountDetails(map);

    }
    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testGetAccountDetailsWithData() throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "1");
        map.put("sortCode", "122312");
        assertNotNull(accountInfoController.getAccountDetails(map));
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateAccountDetails() throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        assertNotNull(accountInfoController.createAccountDetails(
                transactionVos, "200013"));
    }

    // updateAccountDetails

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testUpdateAccountDetails() throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        AccountInfoVo accountInfoVo = accountInfoController
                .createAccountDetails(transactionVos, "200013");

        assertNotNull(accountInfoController.updateAccountDetails(
                transactionVos, "200013",
                String.valueOf(accountInfoVo.getAccountNumber())));
    }

    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testUpdateAccountDetailsInvalidSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.updateAccountDetails(transactionVos, "20004313",
                "2");
    }
    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testUpdateAccountDetailsAlphaNumericSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.updateAccountDetails(transactionVos, "fdsf23",
                "2");
    }

    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testUpdateAccountDetailsAlphaNumericAccountNumber()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.updateAccountDetails(transactionVos, "200044",
                "2re");
    }

    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateAccountDetailsInvalidSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.createAccountDetails(transactionVos, "2000134");
    }
    @Test(expected = ApplicationException.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testCreateAccountDetailsAlphaNumericSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.createAccountDetails(transactionVos, "3cds");
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testExceptionHandler() throws ApplicationException {
        assertNotNull(accountInfoController.exceptionHandler(new Exception(
                "Error")));
    }
}
