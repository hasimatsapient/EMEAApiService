package com.emea.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.emea.dao.AccountInfoDao;
import com.emea.dto.AccountInfoVo;
import com.emea.exception.ApplicationException;
import com.emea.model.AccountInfoBo;
import com.emea.util.CommonUtil;
import com.emea.util.CommonUtility;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommonUtil.class)
public class AccountInfoServicePowerMockTest {
    private AccountInfoService accountInfoService;

    @Mock
    AccountInfoDao accountInfoDao;
    @Mock
    ValidationService validationService;
    

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CommonUtil.class);
        accountInfoService = new AccountInfoServiceImpl();
        CommonUtility.setPrivateField(
                ((AccountInfoServiceImpl) accountInfoService).getClass(),
                accountInfoService, "accountInfoDao", accountInfoDao);
        CommonUtility.setPrivateField(
                ((AccountInfoServiceImpl) accountInfoService).getClass(),
                accountInfoService, "validationService", validationService);

    }

    @Test
    public void testCreateAccountDetailsWithvalidData()
            throws ApplicationException {

        AccountInfoVo accountInfoVoInput = new AccountInfoVo();
        // accountInfoVoInput.setAccountNumber(1L);
        accountInfoVoInput.setSortCode(122312L);
        Mockito.when(
                validationService.validate(Matchers.any(AccountInfoVo.class)))
                .thenReturn(Boolean.TRUE);
       

        AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312L);
        Mockito.when(
                accountInfoDao.getAccountInfoByAccountNumberAndSortCode(
                        anyLong(), anyLong())).thenReturn(accountInfoBo);
        Mockito.when(
                accountInfoDao.createAccountInfo(Matchers
                        .any(AccountInfoBo.class))).thenReturn(accountInfoBo);
        PowerMockito.when(
                CommonUtil.convertAccountInfoVoToBo(
                        Matchers.any(AccountInfoVo.class),
                        Matchers.any(Boolean.class))).thenReturn(accountInfoBo);
        
        PowerMockito.when(
                CommonUtil.convertAccountInfoBoToVo(
                        Matchers.any(AccountInfoBo.class))).thenReturn(accountInfoVoInput);
        AccountInfoVo accountInfoVo = accountInfoService
                .createOrUpdateAccountDetails(accountInfoVoInput);
        assertNotNull(accountInfoVo);

    }
}
