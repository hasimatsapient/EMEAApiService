package com.emea.service;

import static org.mockito.Matchers.anyLong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.emea.dao.AccountInfoDao;
import com.emea.dao.SortCodeDao;
import com.emea.dto.AccountInfoVo;
import com.emea.model.AccountInfoBo;
import com.emea.model.SortCodeBo;
import com.emea.util.CommonUtility;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {
    private ValidationService validationService;

    @Mock
    AccountInfoDao accountInfoDao;

    @Mock
    SortCodeDao sortCodeDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validationService = new ValidationServiceImpl();
        CommonUtility.setPrivateField(
                ((ValidationServiceImpl) validationService).getClass(),
                validationService, "accountInfoDao", accountInfoDao);
        CommonUtility.setPrivateField(
                ((ValidationServiceImpl) validationService).getClass(),
                validationService, "sortCodeDao", sortCodeDao);

    }

    @Test
    public void testValidateWithSortCodeAccountNumber() {

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(122312L);
        AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312);
        Mockito.when(
                accountInfoDao.getAccountInfoByAccountNumberAndSortCode(
                        anyLong(), anyLong())).thenReturn(accountInfoBo);

        boolean flag = validationService.validate(accountInfoVo);
        Assert.assertTrue(flag);

        Mockito.when(
                accountInfoDao.getAccountInfoByAccountNumberAndSortCode(
                        anyLong(), anyLong())).thenReturn(null);

        flag = validationService.validate(accountInfoVo);
        Assert.assertFalse(flag);

    }

    @Test
    public void testValidateWithSortCodeAccountNumberInvlid() {

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(122312L);
        Mockito.when(
                accountInfoDao.getAccountInfoByAccountNumberAndSortCode(
                        anyLong(), anyLong())).thenReturn(null);

        boolean flag = validationService.validate(accountInfoVo);
        Assert.assertFalse(flag);

    }

    @Test
    public void testValidateWithSortCodeOnly() {

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setSortCode(122312L);
        AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312);
        SortCodeBo sortCodeBo = new SortCodeBo();

        Mockito.when(sortCodeDao.getSortCode(anyLong())).thenReturn(sortCodeBo);

        boolean flag = validationService.validate(accountInfoVo);

        Assert.assertTrue(flag);

        Mockito.when(sortCodeDao.getSortCode(anyLong())).thenReturn(null);

        flag = validationService.validate(accountInfoVo);
        Assert.assertFalse(flag);

    }

    @Test
    public void testValidateWithSortCodeOnlyInvalidData() {

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setSortCode(122312L);
        Mockito.when(sortCodeDao.getSortCode(anyLong())).thenReturn(null);

        boolean flag = validationService.validate(accountInfoVo);
        Assert.assertFalse(flag);

    }
    
    @Test
    public void testValidateWithNoSortCodeAndAccountNumber() {

        AccountInfoVo accountInfoVo = new AccountInfoVo();
        boolean flag = validationService.validate(accountInfoVo);
        Assert.assertFalse(flag);

    }

}
