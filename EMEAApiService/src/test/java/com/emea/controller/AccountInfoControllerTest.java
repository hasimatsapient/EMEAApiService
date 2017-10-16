package com.emea.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.emea.dto.AccountInfoVo;
import com.emea.dto.TransactionVo;
import com.emea.exception.ApplicationException;
import com.emea.service.AccountInfoService;
import com.emea.util.CommonUtility;

@RunWith(MockitoJUnitRunner.class)
public class AccountInfoControllerTest {
    private AccountInfoController accountInfoController;
    @Mock
    AccountInfoService accountInfoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountInfoController = new AccountInfoController();
        CommonUtility
                .setPrivateField((accountInfoController).getClass(),
                        accountInfoController, "accountInfoService",
                        accountInfoService);
        CommonUtility.setPrivateField((accountInfoController).getClass(),
                accountInfoController, "gitHubUrl",
                "https://api.github.com/users/hadley/orgs");
        CommonUtility.setPrivateField((accountInfoController).getClass(),
                accountInfoController, "connectionTimeOut", "1000");

    }

    @Test
    public void testGetAccountDetailsData() throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        map.put("sortCode", "122312");

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(2L);
        accountInfoVo.setSortCode(122312L);
        Mockito.when(
                accountInfoService.getAccountDetails(Matchers.anyLong(),
                        Matchers.anyLong())).thenReturn(accountInfoVo);

        Map result = accountInfoController.getAccountDetails(map);
        assertNotNull(result);

    }
    @Test(expected = ApplicationException.class)
    public void testGetAccountDetailsWithNoAccountNumber()
            throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("sortCode", "122312");
        accountInfoController.getAccountDetails(map);

    }
    @Test(expected = ApplicationException.class)
    public void testGetAccountDetailsWithNoSortCode()
            throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        accountInfoController.getAccountDetails(map);

    }
    @Test(expected = ApplicationException.class)
    public void testGetAccountDetailsWithNoData() throws ApplicationException {
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        map.put("sortCode", "122312");

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(2L);
        accountInfoVo.setSortCode(122312L);
        Mockito.when(
                accountInfoService.getAccountDetails(Matchers.anyLong(),
                        Matchers.anyLong())).thenReturn(null);

        Map result = accountInfoController.getAccountDetails(map);
    }

    @Test
    public void testCreateAccountDetails() throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        map.put("sortCode", "122312");

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(2L);
        accountInfoVo.setSortCode(122312L);
        Mockito.when(
                accountInfoService.createOrUpdateAccountDetails(Matchers
                        .any(AccountInfoVo.class))).thenReturn(accountInfoVo);

        assertNotNull(accountInfoController.createAccountDetails(
                transactionVos, "200013"));
    }

    // updateAccountDetails

    @Test
    public void testUpdateAccountDetails() throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", "2");
        map.put("sortCode", "122312");

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(2L);
        accountInfoVo.setSortCode(122312L);
        Mockito.when(
                accountInfoService.createOrUpdateAccountDetails(Matchers
                        .any(AccountInfoVo.class))).thenReturn(accountInfoVo);

        assertNotNull(accountInfoController.updateAccountDetails(
                transactionVos, "200013",
                String.valueOf(accountInfoVo.getAccountNumber())));

    }

    @Test(expected = ApplicationException.class)
    public void testUpdateAccountDetailsInvalidSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.updateAccountDetails(transactionVos, "20004313",
                "2");
    }
     @Test(expected = ApplicationException.class)
    public void testUpdateAccountDetailsAlphaNumericSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.updateAccountDetails(transactionVos, "fdsf23",
                "2");
    }

     @Test(expected = ApplicationException.class)
    public void testUpdateAccountDetailsAlphaNumericAccountNumber()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.updateAccountDetails(transactionVos, "200044",
                "2re");
    }

     @Test(expected = ApplicationException.class)
    public void testCreateAccountDetailsInvalidSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.createAccountDetails(transactionVos, "2000134");
    }
     @Test(expected = ApplicationException.class)
    public void testCreateAccountDetailsAlphaNumericSortCode()
            throws ApplicationException {
        List<TransactionVo> transactionVos = new ArrayList<>();
        accountInfoController.createAccountDetails(transactionVos, "3cds");
    }

     @Test
    public void testExceptionHandler() throws ApplicationException {
        assertNotNull(accountInfoController.exceptionHandler(new Exception(
                "Error")));
    }
}
